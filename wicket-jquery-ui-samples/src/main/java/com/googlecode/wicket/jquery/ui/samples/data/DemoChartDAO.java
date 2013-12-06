package com.googlecode.wicket.jquery.ui.samples.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoChartDAO
{
	private static final Logger LOG = LoggerFactory.getLogger(DemoChartDAO.class);
	private static final String db_file = "chart.db";

	private static DemoChartDAO instance = null;

	public static synchronized DemoChartDAO get()
	{
		if (instance == null)
		{
			instance = new DemoChartDAO();
		}

		return instance;
	}

	public static synchronized void end()
	{
		Connection connection = get().connection;

		if (connection != null)
		{
			try
			{
				Statement statement = get().connection.createStatement();
				statement.execute("SHUTDOWN");

				connection.close();
			}
			catch (SQLException e)
			{
				LOG.error(e.getMessage(), e);
			}
		}
	}

	private Connection connection = null;

	DemoChartDAO()
	{
		this(db_file);
	}

	DemoChartDAO(String db_file)
	{
		this.openDB(db_file);
	}

	private final void openDB(String file)
	{
		try
		{
			Class.forName("org.hsqldb.jdbcDriver");
			this.connection = DriverManager.getConnection("jdbc:hsqldb:" + file, "sa", "");
		}
		catch (ClassNotFoundException e)
		{
			LOG.error(e.getMessage(), e);
		}
		catch (SQLException e)
		{
			LOG.error(e.getMessage(), e);
		}

		if (this.connection != null)
		{
			this.initDB();
		}
	}

	private final void initDB()
	{
		// creates table //
		try
		{
			this.update("CREATE TABLE chart_data (id INTEGER IDENTITY, seriesId INTEGER, value INTEGER)");
		}
		catch (SQLException e)
		{
			LOG.error(e.getMessage(), e);
		}

		// inserts data //
		try
		{
			this.update("INSERT INTO chart_data(seriesId, value) VALUES(1, 45)");
			this.update("INSERT INTO chart_data(seriesId, value) VALUES(1, 54)");
			this.update("INSERT INTO chart_data(seriesId, value) VALUES(1, 96)");
			this.update("INSERT INTO chart_data(seriesId, value) VALUES(1, 52)");
			this.update("INSERT INTO chart_data(seriesId, value) VALUES(1, 15)");
			this.update("INSERT INTO chart_data(seriesId, value) VALUES(1, 66)");
			this.update("INSERT INTO chart_data(seriesId, value) VALUES(1, 44)");
			this.update("INSERT INTO chart_data(seriesId, value) VALUES(1, 85)");
			this.update("INSERT INTO chart_data(seriesId, value) VALUES(1, 35)");
			this.update("INSERT INTO chart_data(seriesId, value) VALUES(1, 95)");

			this.update("INSERT INTO chart_data(seriesId, value) VALUES(2, 15)");
			this.update("INSERT INTO chart_data(seriesId, value) VALUES(2, 65)");
			this.update("INSERT INTO chart_data(seriesId, value) VALUES(2, 25)");
			this.update("INSERT INTO chart_data(seriesId, value) VALUES(2, 78)");
			this.update("INSERT INTO chart_data(seriesId, value) VALUES(2, 12)");
			this.update("INSERT INTO chart_data(seriesId, value) VALUES(2, 89)");
			this.update("INSERT INTO chart_data(seriesId, value) VALUES(2, 26)");
			this.update("INSERT INTO chart_data(seriesId, value) VALUES(2, 75)");
			this.update("INSERT INTO chart_data(seriesId, value) VALUES(2, 65)");
			this.update("INSERT INTO chart_data(seriesId, value) VALUES(2, 35)");
		}
		catch (SQLException e)
		{
			LOG.error(e.getMessage(), e);
		}
	}

	public synchronized void update(String expression) throws SQLException
	{
		Statement statement = null;

		try
		{
			statement = this.connection.createStatement();
			int count = statement.executeUpdate(expression);

			if (count == -1)
			{
				throw new SQLException("db-update error: " + expression);
			}
		}
		finally
		{
			if (statement != null)
			{
				statement.close();
			}
		}
	}

	// Properties //
	public Connection getConnection()
	{
		return this.connection;
	}
}
