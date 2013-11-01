package com.googlecode.wicket.jquery.ui.samples.pages.chart;

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
				new DemoLink(DefaultChartPage.class, "Chart: sample 1"),
				new DemoLink(GalleryChartPage.class, "Chart: multiple chart types (galleries)"),
				new DemoLink(DateChartPage.class, "Chart: using date")
			);
	}
}
