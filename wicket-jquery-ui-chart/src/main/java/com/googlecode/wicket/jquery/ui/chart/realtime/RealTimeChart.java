/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.googlecode.wicket.jquery.ui.chart.realtime;

import org.apache.wicket.ajax.AbstractAjaxTimerBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.time.Duration;

import com.googlecode.wicket.jquery.core.JQueryBehavior;
import com.googlecode.wicket.jquery.core.Options;
import com.googlecode.wicket.jquery.ui.chart.AbstractChart;
import com.googlecode.wicket.jquery.ui.chart.Gallery;

/**
 * Provides a chart with realtime capabilities<br/>
 * Default duration is 1 second
 *
 * @author Sebastien Briquet - sebfz1
 *
 */
public abstract class RealTimeChart extends AbstractChart
{
	private static final long serialVersionUID = 1L;

	private Duration duration = Duration.ONE_SECOND;

	private final RealTimeSettings realTimeSettings = new RealTimeSettings();
	private transient Number[] values = new Number[] {};

	/**
	 * Constructor
	 *
	 * @param id the markup id
	 */
	public RealTimeChart(String id)
	{
		this(id, Gallery.None, new Options());
	}

	/**
	 * Constructor
	 *
	 * @param id the markup id
	 * @param gallery the {@link Gallery}
	 */
	public RealTimeChart(String id, Gallery gallery)
	{
		this(id, gallery, new Options());
	}

	/**
	 * Constructor
	 *
	 * @param id the markup id
	 * @param options {@link Options}
	 */
	public RealTimeChart(String id, Options options)
	{
		this(id, Gallery.None, options);
	}

	/**
	 * Constructor
	 *
	 * @param id the markup id
	 * @param gallery the {@link Gallery}
	 * @param options {@link Options}
	 */
	public RealTimeChart(String id, Gallery gallery, Options options)
	{
		super(id, gallery, options);
	}

	// Properties //
	/**
	 * Sets a value for a single series
	 *
	 * @param value the value
	 */
	public void setValue(Number value)
	{
		this.setValues(new Number[] { value });
	}

	/**
	 * Sets values for multiple series
	 *
	 * @param values values
	 */
	public void setValues(Number... values)
	{
		this.values = values;
	}

	/**
	 * Gets values
	 *
	 * @return values
	 */
	public Number[] getValues()
	{
		return this.values;
	}

	// Events //
	@Override
	protected void onInitialize()
	{
		super.onInitialize();

		this.add(new AbstractAjaxTimerBehavior(this.duration) {

			private static final long serialVersionUID = 1L;

			private int i = 0;

			@Override
			protected void onTimer(AjaxRequestTarget target)
			{
				RealTimeChart.this.onInterval(target, i++);

				target.appendJavaScript(getStatement());
			}
		});
	}

	@Override
	public void onConfigure(JQueryBehavior behavior)
	{
		super.onConfigure(behavior);

		behavior.setOption("realTime", this.getRealTimeSettings());
		behavior.setOption("data", "{ series: 2 }");
		// FIXME: issue with bufferSize

		// behavior.setOption("data", String.format("{ series: %s }", this.series.size()));
		// chart1.getDataSourceSettings().reloadData();
	}

	/**
	 * Triggered on every duration interval<br/>
	 * This method is used to sets new values using {@link #setValue(Number)} or {@link #setValues(Number...)}
	 *
	 * @param target the {@link AjaxRequestTarget}
	 * @param sequence the sequence number
	 */
	protected abstract void onInterval(AjaxRequestTarget target, int sequence);

	/**
	 * TODO to be renamed
	 *
	 * @return
	 */
	protected CharSequence getStatement()
	{
		StringBuilder builder = new StringBuilder();

		// TODO: update when chaining will be available
		String selector = JQueryWidget.getSelector(this);
		builder.append(String.format("jQuery('%s').chart('getRealTime').beginAddData(1, cfx.RealTimeAction.Append);", selector));

		for (int i = 0; i < this.values.length; i++)
		{
			builder.append(String.format("jQuery('%s').chart('getData').setItem(%d, 0, %f);", selector, i, this.values[i]));
		}

		builder.append(String.format("jQuery('%s').chart('getRealTime').endAddData(true, true);", selector));

		return builder;
	}

	// Properties //
	/**
	 * Gets the {@link Duration}
	 *
	 * @return the {@link Duration}
	 */
	public Duration getDuration()
	{
		return this.duration;
	}

	/**
	 * Sets the {@link Duration}
	 *
	 * @param duration the {@link Duration}
	 */
	public void setDuration(Duration duration)
	{
		this.duration = Args.notNull(duration, "duration");
	}

	// RealTimeSettings //
	/**
	 * Gets the {@link RealTimeSettings}
	 *
	 * @return the {@link RealTimeSettings}
	 */
	public RealTimeSettings getRealTimeSettings()
	{
		return this.realTimeSettings;
	}

	/**
	 * Provides the realtime settings options
	 */
	public static class RealTimeSettings extends Options
	{
		private static final long serialVersionUID = 1L;

		/**
		 * Sets the {@link RealTimeMode}
		 *
		 * @param mode the {@link RealTimeMode}
		 */
		public void setMode(RealTimeMode mode)
		{
			this.set("mode", mode);
		}

		/**
		 * Sets the buffer size
		 *
		 * @param size the buffer size
		 */
		public void setBufferSize(int size)
		{
			this.set("bufferSize", size);
		}
	}
}
