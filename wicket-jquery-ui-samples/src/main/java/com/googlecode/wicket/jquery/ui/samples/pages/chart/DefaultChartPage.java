package com.googlecode.wicket.jquery.ui.samples.pages.chart;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.wicket.jquery.core.Options;
import com.googlecode.wicket.jquery.ui.chart.Chart;
import com.googlecode.wicket.jquery.ui.chart.ChartModel;

public class DefaultChartPage extends AbstractChartPage
{
	private static final long serialVersionUID = 1L;

	public DefaultChartPage()
	{
		this.init();
	}

	private void init()
	{
		// Chart //

		Chart chart = new Chart("chart", newChartModel());
		// chart.add(new );
		this.add(chart);
	}

	private static ChartModel<?> newChartModel()
	{
		return new ChartModel<BikeStats>() {

			private static final long serialVersionUID = 1L;

			@Override
			protected List<BikeStats> load()
			{
				List<BikeStats> list = new ArrayList<BikeStats>();

				list.add(new BikeStats("Jan", 1800));
				list.add(new BikeStats("Feb", 1760));
				list.add(new BikeStats("Mar", 1740));
				list.add(new BikeStats("Apr", 1750));
				list.add(new BikeStats("May", 1810));
				list.add(new BikeStats("Jun", 1920));

				return list;
			}

			@Override
			protected void toJson(StringBuilder builder, BikeStats bean)
			{
				builder.append("{ ");
				builder.append(Options.QUOTE).append("month").append(Options.QUOTE).append(": ");
				builder.append(Options.QUOTE).append(bean.getMonth()).append(Options.QUOTE);

				Number[] values = bean.getValues();
				for (int i = 0; i < values.length; i++)
				{
					Number value = values[i];

					builder.append(", ");
					builder.append(Options.QUOTE).append("s").append(i).append(Options.QUOTE).append(": ");
					builder.append(value);
				}

				builder.append(" }");
			}
		};
	}

	static class BikeStats
	{
		private final String month;
		private final Number[] values;

		/**
		 * @param month
		 * @param values
		 */
		public BikeStats(String month, Number... values)
		{
			this.month = month;
			this.values = values;
		}

		public String getMonth()
		{
			return this.month;
		}

		public Number[] getValues()
		{
			return this.values;
		}
	}

}
