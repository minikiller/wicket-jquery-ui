package com.googlecode.wicket.jquery.ui.samples.pages.chart.lines;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.wicket.jquery.ui.chart.Chart;
import com.googlecode.wicket.jquery.ui.chart.Gallery;
import com.googlecode.wicket.jquery.ui.chart.Series;
import com.googlecode.wicket.jquery.ui.chart.data.ChartData;
import com.googlecode.wicket.jquery.ui.chart.data.ChartDataHelper;
import com.googlecode.wicket.jquery.ui.chart.model.ChartModel;
import com.googlecode.wicket.jquery.ui.chart.model.SqlChartModel;
import com.googlecode.wicket.jquery.ui.samples.data.DemoChartDAO;

public class DatabaseChartPage extends AbstractChartPage
{
	private static final long serialVersionUID = 1L;

	public DatabaseChartPage()
	{
		// Chart //
		Chart chart = new Chart("chart", newChartModel(), Gallery.Lines);
		chart.add(new Series("My Series #1"));
		chart.add(new Series("My Series #2"));

		this.add(chart);
	}

	private static ChartModel newChartModel()
	{
		return new SqlChartModel() {

			private static final long serialVersionUID = 1L;
			private static final String QUERY = "SELECT c.name, d.value, d.seriesId FROM categories c INNER JOIN data d ON c.id = d.categoryId";

			@Override
			protected List<ChartData> query() throws SQLException
			{
				List<ChartData> list = new ArrayList<ChartData>();
				Statement statement = null;

				try
				{
					statement = DemoChartDAO.get().getConnection().createStatement();

					ResultSet rs = statement.executeQuery(QUERY);
					ResultSetMetaData meta = rs.getMetaData();
					int cols = meta.getColumnCount();

					if (cols > 1)
					{
						while (rs.next())
						{
							ChartDataHelper.add(list, rs.getString(1), rs.getInt(2));
						}
					}
				}
				finally
				{
					if (statement != null)
					{
						statement.close();
					}
				}

				return list;
			}

			@Override
			protected void onSqlException(SQLException e)
			{
				// noop
			}

			@Override
			public void detach()
			{
				super.detach();

				DemoChartDAO.end();
			}
		};
	}
}
