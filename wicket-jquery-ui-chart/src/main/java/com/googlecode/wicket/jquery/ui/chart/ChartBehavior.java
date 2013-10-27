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

import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.settings.IJavaScriptLibrarySettings;

import com.googlecode.wicket.jquery.core.JQueryBehavior;
import com.googlecode.wicket.jquery.core.JQueryEvent;
import com.googlecode.wicket.jquery.core.Options;
import com.googlecode.wicket.jquery.core.ajax.IJQueryAjaxAware;
import com.googlecode.wicket.jquery.ui.chart.settings.ChartLibrarySettings;
import com.googlecode.wicket.jquery.ui.chart.settings.IChartLibrarySettings;

/**
 * Provides the jQuery fullCalendar behavior
 *
 * @author Sebastien Briquet - sebfz1
 *
 */
public class ChartBehavior extends JQueryBehavior implements IJQueryAjaxAware
{
	private static final long serialVersionUID = 1L;
	private static final String METHOD = "chart";

	/**
	 * Gets the {@link IChartLibrarySettings}
	 *
	 * @return Default internal {@link IChartLibrarySettings} instance if {@link Application}'s {@link IJavaScriptLibrarySettings} is not an instance of {@link IChartLibrarySettings}
	 */
	private static IChartLibrarySettings getLibrarySettings()
	{
		if (Application.exists() && (Application.get().getJavaScriptLibrarySettings() instanceof IChartLibrarySettings))
		{
			return (IChartLibrarySettings) Application.get().getJavaScriptLibrarySettings();
		}

		return ChartLibrarySettings.get();
	}

	/**
	 * Constructor
	 *
	 * @param selector the html selector (ie: "#myId")
	 */
	public ChartBehavior(final String selector)
	{
		this(selector, new Options());
	}

	/**
	 * Constructor
	 *
	 * @param selector the html selector (ie: "#myId")
	 * @param options the {@link Options}
	 */
	public ChartBehavior(final String selector, Options options)
	{
		super(selector, METHOD, options);

		this.initReferences();
	}

	/**
	 * Initializes CSS & JavaScript resource references
	 */
	private void initReferences()
	{
		IChartLibrarySettings settings = getLibrarySettings();

		// jchartfx.css //
		if (settings.getChartStyleSheetReference() != null)
		{
			this.add(settings.getChartStyleSheetReference());
		}

		// jchartfx.full.css //
//		if (settings.getChartJavaScriptReference() != null)
//		{
//			this.add(settings.getChartJavaScriptReference());
//		}

		// jchartfx.xxx.js //
		this.add(new JavaScriptResourceReference(ChartBehavior.class, "resource/js/jchartfx.system.js"));
		this.add(new JavaScriptResourceReference(ChartBehavior.class, "resource/js/jchartfx.coreBasic.js"));
		this.add(new JavaScriptResourceReference(ChartBehavior.class, "resource/js/jchartfx.coreVector.js"));

		this.add(new JavaScriptResourceReference(ChartBehavior.class, "resource/js/jchartfx.advanced.js"));
		this.add(new JavaScriptResourceReference(ChartBehavior.class, "resource/js/jchartfx.data.js"));
		this.add(new JavaScriptResourceReference(ChartBehavior.class, "resource/js/jchartfx.vector.js"));

		this.add(new JavaScriptResourceReference(ChartBehavior.class, "resource/js/jchartfx.ui.js"));
	}

	// Methods //
	@Override
	public void bind(Component component)
	{
		super.bind(component);

	}

	@Override
	public void renderHead(Component component, IHeaderResponse response)
	{
		super.renderHead(component, response);

//		IRequestHandler handler = new ResourceReferenceRequestHandler(AbstractDefaultAjaxBehavior.INDICATOR);
//
//		/* adds and configure the busy indicator */
//		StringBuilder builder = new StringBuilder();
//
//		builder.append("jQuery(function(){\n");
//		builder.append("jQuery(\"<img id='calendar-indicator' src='").append(RequestCycle.get().urlFor(handler)).append("' />\").appendTo('.fc-header-center');\n"); // allows only one calendar.
//		builder.append("jQuery(document).ajaxStart(function() { jQuery('#calendar-indicator').show(); });\n");
//		builder.append("jQuery(document).ajaxStop(function() { jQuery('#calendar-indicator').hide(); });\n");
//		builder.append("});\n");
//
//		response.render(JavaScriptHeaderItem.forScript(builder, this.getClass().getSimpleName() + "-indicator"));
	}

	// Events //
	@Override
	public void onConfigure(Component component)
	{
		super.onConfigure(component);

//		this.setOption("viewRender", this.onViewRenderBehavior.getCallbackFunction());
	}

	@Override
	public void onAjax(AjaxRequestTarget target, JQueryEvent event)
	{

	}
}
