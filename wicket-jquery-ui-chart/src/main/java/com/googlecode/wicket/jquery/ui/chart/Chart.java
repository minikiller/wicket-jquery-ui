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
package com.googlecode.wicket.jquery.ui.chart;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.wicket.jquery.core.JQueryBehavior;
import com.googlecode.wicket.jquery.core.Options;
import com.googlecode.wicket.jquery.ui.chart.data.ChartData;
import com.googlecode.wicket.jquery.ui.chart.model.ChartModel;

/**
 * Provides a chart widget, based on jChartFX.
 *
 * @author Sebastien Briquet - sebfz1
 *
 */
public class Chart extends AbstractChart
{
	private static final long serialVersionUID = 1L;

	private final List<Series> series = new ArrayList<Series>();

	/**
	 * Constructor
	 *
	 * @param id the markup id
	 */
	public Chart(String id)
	{
		super(id);
	}

	/**
	 * Constructor
	 *
	 * @param id the markup id
	 * @param gallery the {@link Gallery}
	 */
	public Chart(String id, Gallery gallery)
	{
		super(id, gallery);
	}

	/**
	 * Constructor
	 *
	 * @param id the markup id
	 * @param options {@link Options}
	 */
	public Chart(String id, Options options)
	{
		super(id, options);
	}

	/**
	 * Constructor
	 *
	 * @param id the markup id
	 * @param gallery the {@link Gallery}
	 * @param options {@link Options}
	 */
	public Chart(String id, Gallery gallery, Options options)
	{
		super(id, gallery, options);
	}

	/**
	 * Constructor
	 *
	 * @param id the markup id
	 * @param model the {@link ChartModel}
	 */
	public Chart(String id, ChartModel model)
	{
		this(id, model, Gallery.None, new Options());
	}

	/**
	 * Constructor
	 *
	 * @param id the markup id
	 * @param model the {@link ChartModel}
	 * @param gallery the {@link Gallery}
	 */
	public Chart(String id, ChartModel model, Gallery gallery)
	{
		this(id, model, gallery, new Options());
	}

	/**
	 * Constructor
	 *
	 * @param id the markup id
	 * @param model the {@link ChartModel}
	 * @param options {@link Options}
	 */
	public Chart(String id, ChartModel model, Options options)
	{
		this(id, model, Gallery.None, options);
	}

	/**
	 * Constructor
	 *
	 * @param id the markup id
	 * @param model the {@link ChartModel}
	 * @param gallery the {@link Gallery}
	 * @param options {@link Options}
	 */
	public Chart(String id, ChartModel model, Gallery gallery, Options options)
	{
		super(id, gallery, options);

		this.setModel(model);
	}

	// Properties //

	/**
	 * Gets the {@link ChartModel}
	 *
	 * @return the {@link ChartModel}
	 */
	public final ChartModel getModel()
	{
		return (ChartModel) this.getDefaultModel();
	}

	/**
	 * Sets the {@link ChartModel}
	 *
	 * @param model the {@link ChartModel}
	 */
	public final void setModel(ChartModel model)
	{
		this.setDefaultModel(model);
	}

	/**
	 * Gets the underlying {@link List} of {@link ChartData}
	 *
	 * @return the {@link List} of {@link ChartData}
	 */
	@SuppressWarnings("unchecked")
	public List<ChartData> getModelObject()
	{
		return (List<ChartData>) this.getDefaultModelObject();
	}

	// Events //
	@Override
	protected void onInitialize()
	{
		super.onInitialize();
	}

	@Override
	public void onConfigure(JQueryBehavior behavior)
	{
		super.onConfigure(behavior);

		// dataSource
		behavior.setOption("dataValues", this.getModelObject().toString());
		behavior.setOption("series", this.series.toString()); // should be set *after* dataValues
		//Series.toJson

		// behavior.setOption("data", String.format("{ series: %s }", this.series.size()));
		// chart1.getDataSourceSettings().reloadData();
	}

	// Methods //
	public void add(Series series)
	{
		this.series.add(series);
	}
}
