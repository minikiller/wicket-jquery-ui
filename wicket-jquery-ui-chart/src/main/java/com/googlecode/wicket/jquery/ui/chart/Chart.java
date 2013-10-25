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

	public static String toJson(ChartModel<?> model)
	{
		if (model != null)
		{
			return String.valueOf(model.toJson());
		}

		return "";
	}


	private final Options options;

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
	}

	/**
	 * Constructor
	 * @param id the markup id
	 * @param model
	 */
	public Chart(String id, ChartModel<?> model)
	{
		this(id, model, new Options());
	}

	/**
	 * Constructor
	 * @param id the markup id
	 * @param model
	 * @param options {@link Options}
	 */
	public Chart(String id, ChartModel<?> model, Options options)
	{
		super(id, model);

		this.options = options;
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

//	    chart1.getData().setSeries(2);
//	      chart1.getAxisY().setMin(500);
//	      chart1.getAxisY().setMax(2000);
//	      var series1 = chart1.getSeries().getItem(0);
//	      var series2 = chart1.getSeries().getItem(1);

//		String data = "[{ 'Month': 'Jan', 'Bikes': 1800, 'Parts': 1300 },{ 'Month': 'Feb', 'Bikes': 1760, 'Parts': 900 },{ 'Month': 'Mar', 'Bikes': 1740, 'Parts': 970 },{ 'Month': 'Apr', 'Bikes': 1750, 'Parts': 1010},{ 'Month': 'May', 'Bikes': 1810, 'Parts':1070 },{ 'Month': 'Jun', 'Bikes': 1920, 'Parts': 1180 }]";
//TODO: move to behavior?
//		behavior.setOption("gallery", "cfx.Gallery.Pie"); //Lines
		behavior.setOption("dataValues", Chart.toJson(this.getModel()));

		behavior.setOption("data", "{ series: 2 }");
		behavior.setOption("series", "[{ gallery: cfx.Gallery.Lines, text: 'My serie' }]");
//		behavior.setOption("data", "{ series: 2 }");
//		behavior.setOption("series", "[{ gallery: cfx.Gallery.Lines}, { gallery: cfx.Gallery.Lines}]");

//chart1.getDataSourceSettings().reloadData();

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


	// Factory methods //
}
