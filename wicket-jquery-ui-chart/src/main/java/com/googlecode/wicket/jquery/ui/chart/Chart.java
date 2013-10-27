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
import com.googlecode.wicket.jquery.core.JQueryContainer;
import com.googlecode.wicket.jquery.core.Options;

/**
 * Provides calendar widget, based on the jQuery fullcalendar plugin.
 *
 * @author Sebastien Briquet - sebfz1
 *
 */
public class Chart extends JQueryContainer
{
	private static final long serialVersionUID = 1L;

	private final Options options;
	private final List<Series> series;

	private ChartGallery gallery; // may be null

	/**
	 * Constructor
	 * @param id the markup id
	 */
	public Chart(String id)
	{
		this(id, new Options());
	}

	/**
	 * Constructor
	 * @param id the markup id
	 * @param options {@link Options}
	 */
	public Chart(String id, Options options)
	{
		super(id);

		this.options = options;
		this.series = new ArrayList<Series>();
	}

	//TODO add missing ChartType ctors


	/**
	 * Constructor
	 * @param id the markup id
	 * @param model
	 */
	public Chart(String id, ChartModel<?> model)
	{
		this(id, model, new Options());
	}

	public Chart(String id, ChartModel<?> model, ChartGallery type)
	{
		this(id, model, type, new Options());
	}

	/**
	 * Constructor
	 * @param id the markup id
	 * @param model
	 * @param options {@link Options}
	 */
	public Chart(String id, ChartModel<?> model, Options options)
	{
		this(id, model, null, options);
	}

	/**
	 * Constructor
	 * @param id the markup id
	 * @param model
	 * @param options {@link Options}
	 */
	public Chart(String id, ChartModel<?> model, ChartGallery gallery, Options options)
	{
		super(id, model);

		this.gallery = gallery;
		this.options = options;
		this.series = new ArrayList<Series>();
	}

	// Properties //

	public ChartModel<?> getModel()
	{
		return (ChartModel<?>) this.getDefaultModel();
	}

	public List<?> getModelObject()
	{
		return (List<?>) this.getDefaultModelObject();
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

		//dataSource
		if (this.gallery != null) {
			behavior.setOption("gallery", this.gallery);
		}

		behavior.setOption("dataValues", ChartModel.toJson(this.getModel()));
		behavior.setOption("series", Series.toJson(this.series)); //should be *after* dataValues
        behavior.setOption("dataGrid", "{ visible: true }");

//		behavior.setOption("data", String.format("{ series: %s }", this.series.size()));
//		chart1.getDataSourceSettings().reloadData();
	}


	// IJQueryWidget //
	/**
	 * see {@link JQueryContainer#newWidgetBehavior(String)}
	 */
	@Override
	public JQueryBehavior newWidgetBehavior(String selector)
	{
		return new ChartBehavior(selector, this.options);
	}

	public void add(Series series)
	{
		this.series.add(series);
	}


	// Factory methods //
}
