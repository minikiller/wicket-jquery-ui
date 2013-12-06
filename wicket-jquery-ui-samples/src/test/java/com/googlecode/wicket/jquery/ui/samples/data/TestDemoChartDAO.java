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

	public synchronized static void query(Connection connection, String expression) throws SQLException
	{
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(expression);

		dump(rs);

		statement.close();
	}

	public static void dump(ResultSet rs) throws SQLException
	{

		ResultSetMetaData meta = rs.getMetaData();
		int colmax = meta.getColumnCount();
		int i;
		Object o = null;

		for (; rs.next();)
		{
			for (i = 0; i < colmax; ++i)
			{
				o = rs.getObject(i + 1);
				LOG.info(o.toString() + " ");
			}

			LOG.info(" ");
		}
	}

//	public static void main(String[] args) throws SQLException
//	{
//		DemoChartDAO dao = new DemoChartDAO("chart-test.db");
//
//		query(dao.getConnection(), "SELECT * FROM [values]");
//	}

	@Test
	public void testDB() throws Exception
	{
		DemoChartDAO dao = new DemoChartDAO("chart-test.db");

		query(dao.getConnection(), "SELECT * FROM chart_data");
	}
}
