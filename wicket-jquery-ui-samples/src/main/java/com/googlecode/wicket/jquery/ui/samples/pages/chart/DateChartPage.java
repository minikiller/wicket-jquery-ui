package com.googlecode.wicket.jquery.ui.samples.pages.chart;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.googlecode.wicket.jquery.ui.chart.Chart;
import com.googlecode.wicket.jquery.ui.chart.Gallery;
import com.googlecode.wicket.jquery.ui.chart.ChartModel;
import com.googlecode.wicket.jquery.ui.chart.Series;
import com.googlecode.wicket.jquery.ui.chart.data.SimpleSeriesData;

public class DateChartPage extends AbstractChartPage
{
	private static final long serialVersionUID = 1L;

	public DateChartPage()
	{
		// Chart //

		Chart chart = new Chart("chart", newChartModel(), Gallery.Lines);
		chart.add(new Series("Sample Series #1"));
		chart.add(new Series("Sample Series #2"));

		// chart.setCategoryAxisName("Months");
		this.add(chart);
	}

	private static ChartModel<?> newChartModel()
	{
		return new ChartModel<SimpleSeriesData>() {

			private static final long serialVersionUID = 1L;

			private Calendar calendar = null;

			@Override
			protected List<SimpleSeriesData> load()
			{
				List<SimpleSeriesData> list = new ArrayList<SimpleSeriesData>();

				list.add(new SimpleSeriesData(this.nextDate(), 180, 75));
				list.add(new SimpleSeriesData(this.nextDate(), 176, 55));
				list.add(new SimpleSeriesData(this.nextDate(), 174, 84));
				list.add(new SimpleSeriesData(this.nextDate(), 175, 72));
				list.add(new SimpleSeriesData(this.nextDate(), 181, 54));
				list.add(new SimpleSeriesData(this.nextDate(), 192, 68));
				list.add(new SimpleSeriesData(this.nextDate(), 180, 75));
				list.add(new SimpleSeriesData(this.nextDate(), 176, 55));
				list.add(new SimpleSeriesData(this.nextDate(), 174, 84));
				list.add(new SimpleSeriesData(this.nextDate(), 175, 72));
				list.add(new SimpleSeriesData(this.nextDate(), 181, 54));
				list.add(new SimpleSeriesData(this.nextDate(), 192, 68));

				return list;
			}

			private synchronized Date nextDate()
			{
				if (this.calendar == null)
				{
					this.calendar = Calendar.getInstance();
					this.calendar.set(1900, 0, 1);
				}
				else
				{
//					this.calendar.set(Calendar.MONTH, (int)Math.random() * 12);
					this.calendar.set(Calendar.DAY_OF_YEAR, (int)(Math.random() * 355));
				}

				return this.calendar.getTime();
			}
		};
	}
}
