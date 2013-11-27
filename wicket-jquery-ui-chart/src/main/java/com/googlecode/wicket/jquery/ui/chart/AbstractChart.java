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

import com.googlecode.wicket.jquery.core.JQueryBehavior;
import com.googlecode.wicket.jquery.core.JQueryContainer;
import com.googlecode.wicket.jquery.core.Options;

/**
 * TODO: javadoc
 *
 * @author Sebastien Briquet - sebfz1
 *
 */
public abstract class AbstractChart extends JQueryContainer
{
	private static final long serialVersionUID = 1L;

	private final Gallery gallery;
	private final Options options;

	// chart properties //
	private boolean gridVisible = false;

	/**
	 * Constructor
	 *
	 * @param id the markup id
	 */
	public AbstractChart(String id)
	{
		this(id, Gallery.None, new Options());
	}

	/**
	 * Constructor
	 *
	 * @param id the markup id
	 * @param gallery the {@link Gallery}
	 */
	public AbstractChart(String id, Gallery gallery)
	{
		this(id, gallery, new Options());
	}

	/**
	 * Constructor
	 *
	 * @param id the markup id
	 * @param options {@link Options}
	 */
	public AbstractChart(String id, Options options)
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
	public AbstractChart(String id, Gallery gallery, Options options)
	{
		super(id);

		this.gallery = gallery;
		this.options = options;
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

		// Gallery (general)
		behavior.setOption("gallery", this.gallery);

		behavior.setOption("dataGrid", String.format("{ visible: %b }", this.gridVisible));

		// behavior.setOption("data", String.format("{ series: %s }", this.series.size()));
		// chart1.getDataSourceSettings().reloadData();
	}

	// Methods //
	/**
	 * Indicates whether the data-grid is visible
	 *
	 * @return true or false
	 */
	public boolean isGridVisible()
	{
		return this.gridVisible;
	}

	/**
	 * Sets whether the data-grid should be visible
	 *
	 * @param visible true or false
	 * @return this, for chaining
	 */
	public AbstractChart setGridVisible(boolean visible)
	{
		this.gridVisible = visible;

		return this;
	}

	// IJQueryWidget //
	@Override
	public JQueryBehavior newWidgetBehavior(String selector)
	{
		return new ChartBehavior(selector, this.options);
	}
}
