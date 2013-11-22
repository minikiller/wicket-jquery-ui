package com.googlecode.wicket.jquery.ui.samples;

import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

import com.googlecode.wicket.jquery.core.settings.JQueryLibrarySettings;
import com.googlecode.wicket.jquery.ui.chart.settings.IChartLibrarySettings;

public class SampleLibrarySettings extends JQueryLibrarySettings implements IChartLibrarySettings
{
	@Override
	public ResourceReference getChartStyleSheetReference()
	{
		return new CssResourceReference(SampleLibrarySettings.class, "jchartfx/jchartfx.css");
	}

	@Override
	public ResourceReference getChartJavaScriptReference()
	{
		//http://www.jchartfx.com/jChartFX/7.1.5044.21402/jchartfx.full.js
		return new JavaScriptResourceReference(SampleLibrarySettings.class, "jchartfx/jchartfx.full.js");
	}
}
