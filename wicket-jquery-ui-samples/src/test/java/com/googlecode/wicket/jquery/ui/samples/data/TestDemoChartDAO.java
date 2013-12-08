package com.googlecode.wicket.jquery.ui.samples.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestDemoChartDAO
{
	private static final Logger LOG = LoggerFactory.getLogger(TestDemoChartDAO.class);

	public static void query(Connection connection, String expression) throws SQLException
	{
		Statement statement = null;

		try
		{
			statement = connection.createStatement();

			ResultSet rs = statement.executeQuery(expression);
			dump(rs);
		}
		finally
		{
			if (statement != null)
			{
				statement.close();
			}
		}
	}

	public static void dump(ResultSet rs) throws SQLException
	{
		ResultSetMetaData meta = rs.getMetaData();
		int colmax = meta.getColumnCount();

		while (rs.next())
		{
			String out = "";

			for (int i = 0; i < colmax; ++i)
			{
				out += rs.getObject(i + 1).toString() + " ";
			}

			LOG.info(out);
		}
	}

	public static void main(String[] args) throws SQLException
	{
		new TestDemoChartDAO().testDB();
	}

	@Test
	public void testDB() throws SQLException
	{
		DemoChartDAO dao = new DemoChartDAO("chart-test.db");
		dao.init();

		try
		{
			query(dao.getConnection(), "SELECT c.name, d.seriesId, d.value FROM Categories c INNER JOIN Data d ON c.id = d.categoryId");
		}
		finally
		{
			dao.close();
		}
	}
}
