package com.googlecode.wicket.jquery.ui.samples.pages.chart.lines;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.wicket.jquery.ui.chart.Chart;
import com.googlecode.wicket.jquery.ui.chart.Gallery;
import com.googlecode.wicket.jquery.ui.chart.Series;
import com.googlecode.wicket.jquery.ui.chart.data.ChartData;
import com.googlecode.wicket.jquery.ui.chart.model.ChartModel;

public class GalleryChartPage extends AbstractChartPage
{
	private static final long serialVersionUID = 1L;

	public GalleryChartPage()
	{
		// Chart //
		Chart chart = new Chart("chart", newChartModel());
		chart.add(new Series("My Series #1", Gallery.Lines));
		chart.add(new Series("My Series #2", Gallery.Bar));

		this.add(chart);
	}

	private static ChartModel newChartModel()
	{
		return new ChartModel() {

			private static final long serialVersionUID = 1L;

			@Override
			protected List<ChartData> load()
			{
				List<ChartData> list = new ArrayList<ChartData>();

				list.add(new ChartData("Jan", 180, 75));
				list.add(new ChartData("Feb", 176, 55));
				list.add(new ChartData("Mar", 174, 84));
				list.add(new ChartData("Apr", 175, 72));
				list.add(new ChartData("May", 181, 54));
				list.add(new ChartData("Jun", 192, 68));
				list.add(new ChartData("Jul", 180, 75));
				list.add(new ChartData("Aug", 176, 55));
				list.add(new ChartData("Sep", 174, 84));
				list.add(new ChartData("Oct", 175, 72));
				list.add(new ChartData("Nov", 181, 54));
				list.add(new ChartData("Dec", 192, 68));

				return list;
			}
		};
	}
}
