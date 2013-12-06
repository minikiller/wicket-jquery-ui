package com.googlecode.wicket.jquery.ui.samples.pages.chart.lines;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.wicket.jquery.ui.chart.SimpleChart;
import com.googlecode.wicket.jquery.ui.chart.ChartModel;
import com.googlecode.wicket.jquery.ui.chart.Gallery;
import com.googlecode.wicket.jquery.ui.chart.Series;
import com.googlecode.wicket.jquery.ui.chart.data.CategoryData;

public class DefaultChartPage extends AbstractChartPage
{
	private static final long serialVersionUID = 1L;

	public DefaultChartPage()
	{
		// Chart //
		SimpleChart chart = new SimpleChart("chart", newChartModel(), Gallery.Lines);
		chart.add(new Series("My Series #1"));
		chart.add(new Series("My Series #2"));

//		chart.setCategoryAxisName("Months");
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
