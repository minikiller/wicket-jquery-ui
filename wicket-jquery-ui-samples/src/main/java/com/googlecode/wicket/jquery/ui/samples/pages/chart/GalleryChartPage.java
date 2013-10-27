package com.googlecode.wicket.jquery.ui.samples.pages.chart;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.wicket.jquery.ui.chart.Chart;
import com.googlecode.wicket.jquery.ui.chart.ChartGallery;
import com.googlecode.wicket.jquery.ui.chart.ChartModel;
import com.googlecode.wicket.jquery.ui.chart.Series;
import com.googlecode.wicket.jquery.ui.chart.data.SimpleSeriesData;

public class GalleryChartPage extends AbstractChartPage
{
	private static final long serialVersionUID = 1L;

	public GalleryChartPage()
	{
		// Chart //

		Chart chart = new Chart("chart", newChartModel());
		chart.add(new Series(ChartGallery.Lines, "Sample Series #1"));
		chart.add(new Series(ChartGallery.Bar, "Sample Series #2"));

		this.add(chart);
	}

	private static ChartModel<?> newChartModel()
	{
		return new ChartModel<SimpleSeriesData>() {

			private static final long serialVersionUID = 1L;

			@Override
			protected List<SimpleSeriesData> load()
			{
				List<SimpleSeriesData> list = new ArrayList<SimpleSeriesData>();

				list.add(new SimpleSeriesData("Jan", 180, 75));
				list.add(new SimpleSeriesData("Feb", 176, 55));
				list.add(new SimpleSeriesData("Mar", 174, 84));
				list.add(new SimpleSeriesData("Apr", 175, 72));
				list.add(new SimpleSeriesData("May", 181, 54));
				list.add(new SimpleSeriesData("Jun", 192, 68));
				list.add(new SimpleSeriesData("Jul", 180, 75));
				list.add(new SimpleSeriesData("Aug", 176, 55));
				list.add(new SimpleSeriesData("Sep", 174, 84));
				list.add(new SimpleSeriesData("Oct", 175, 72));
				list.add(new SimpleSeriesData("Nov", 181, 54));
				list.add(new SimpleSeriesData("Dec", 192, 68));

				return list;
			}
		};
	}
}
