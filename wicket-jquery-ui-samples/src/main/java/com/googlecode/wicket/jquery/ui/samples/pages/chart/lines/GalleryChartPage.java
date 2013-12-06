package com.googlecode.wicket.jquery.ui.samples.pages.chart.lines;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.wicket.jquery.ui.chart.SimpleChart;
import com.googlecode.wicket.jquery.ui.chart.ChartModel;
import com.googlecode.wicket.jquery.ui.chart.Gallery;
import com.googlecode.wicket.jquery.ui.chart.Series;
import com.googlecode.wicket.jquery.ui.chart.data.CategoryData;

public class GalleryChartPage extends AbstractChartPage
{
	private static final long serialVersionUID = 1L;

	public GalleryChartPage()
	{
		// Chart //
		SimpleChart chart = new SimpleChart("chart", newChartModel());
		chart.add(new Series("My Series #1", Gallery.Lines));
		chart.add(new Series("My Series #2", Gallery.Bar));

		this.add(chart);
	}

	private static ChartModel<?> newChartModel()
	{
		return new ChartModel<CategoryData>() {

			private static final long serialVersionUID = 1L;

			@Override
			protected List<CategoryData> load()
			{
				List<CategoryData> list = new ArrayList<CategoryData>();

				list.add(new CategoryData("Jan", 180, 75));
				list.add(new CategoryData("Feb", 176, 55));
				list.add(new CategoryData("Mar", 174, 84));
				list.add(new CategoryData("Apr", 175, 72));
				list.add(new CategoryData("May", 181, 54));
				list.add(new CategoryData("Jun", 192, 68));
				list.add(new CategoryData("Jul", 180, 75));
				list.add(new CategoryData("Aug", 176, 55));
				list.add(new CategoryData("Sep", 174, 84));
				list.add(new CategoryData("Oct", 175, 72));
				list.add(new CategoryData("Nov", 181, 54));
				list.add(new CategoryData("Dec", 192, 68));

				return list;
			}
		};
	}
}
