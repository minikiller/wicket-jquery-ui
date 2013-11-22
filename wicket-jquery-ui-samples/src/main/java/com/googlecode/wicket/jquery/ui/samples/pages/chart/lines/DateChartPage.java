package com.googlecode.wicket.jquery.ui.samples.pages.chart.lines;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.googlecode.wicket.jquery.ui.chart.Chart;
import com.googlecode.wicket.jquery.ui.chart.ChartModel;
import com.googlecode.wicket.jquery.ui.chart.Gallery;
import com.googlecode.wicket.jquery.ui.chart.Series;
import com.googlecode.wicket.jquery.ui.chart.data.CategoryData;

public class DateChartPage extends AbstractChartPage
{
	private static final long serialVersionUID = 1L;

	private static Date[] newDates(int count)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(2013, 0, 1);

		if (count > 0)
		{
			Date[] dates = new Date[count];

			for (int i = 0; i < count; i++)
			{
				calendar.set(Calendar.DAY_OF_YEAR, (int)(Math.random() * 355));
				dates[i] = calendar.getTime();
			}

			Arrays.sort(dates);

			return dates;
		}

		return null;
	}

	public DateChartPage()
	{
		// Chart //
		Chart chart = new Chart("chart", newChartModel(), Gallery.Lines);
		chart.setGridVisible(true);
		chart.add(new Series("My Series #1"));
		chart.add(new Series("My Series #2"));

		// chart.setCategoryAxisName("Months");
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

				Date[] dates = newDates(12);

				if (dates != null)
				{
					list.add(new CategoryData(dates[0], 180, 75));
					list.add(new CategoryData(dates[1], 176, 55));
					list.add(new CategoryData(dates[2], 174, 84));
					list.add(new CategoryData(dates[3], 175, 72));
					list.add(new CategoryData(dates[4], 181, 54));
					list.add(new CategoryData(dates[5], 192, 68));
					list.add(new CategoryData(dates[6], 180, 75));
					list.add(new CategoryData(dates[7], 176, 55));
					list.add(new CategoryData(dates[8], 174, 84));
					list.add(new CategoryData(dates[9], 175, 72));
					list.add(new CategoryData(dates[10], 181, 54));
					list.add(new CategoryData(dates[11], 192, 68));
				}

				return list;
			}
		};
	}
}
