package com.googlecode.wicket.jquery.ui.samples.pages.chart.lines;

import java.util.Arrays;
import java.util.List;

import com.googlecode.wicket.jquery.ui.samples.SamplePage;



abstract class AbstractChartPage extends SamplePage
{
	private static final long serialVersionUID = 1L;

	public AbstractChartPage()
	{

	}

	@Override
	protected List<DemoLink> getDemoLinks()
	{
		return Arrays.asList(
				new DemoLink(DefaultChartPage.class, "Chart: Lines"),
				new DemoLink(GalleryChartPage.class, "Chart: Lines & Bars"),
				new DemoLink(DateChartPage.class, "Chart: Lines with dates")
			);
	}
}
