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
	private static boolean initialized = false;

	public static synchronized DemoChartDAO get()
	{
		if (instance == null)
		{
			instance = new DemoChartDAO();
		}

		if (!initialized)
		{
			initialized = instance.init();
		}

		return instance;
	}

	public static synchronized void end()
	{
		if (instance != null)
		{
			instance.close();
			instance = null;
		}
	}

	private Connection connection = null;

	DemoChartDAO()
	{
		this(db_file);
	}

	DemoChartDAO(String db_file)
	{
		this.open(db_file);
	}

	final void open(String file)
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
	}

	final boolean init()
	{
		// drops existing tables //
		try
		{
			this.update("DROP TABLE Data IF EXISTS");
			this.update("DROP TABLE Categories IF EXISTS");
		}
		catch (SQLException e)
		{
			LOG.error(e.getMessage(), e);
		}

		// creates table //
		try
		{
			this.update("CREATE TABLE Categories (id INTEGER, name VARCHAR(3))");
			this.update("CREATE TABLE Data (id INTEGER IDENTITY, seriesId INTEGER, categoryId INTEGER, value INTEGER)");
		}
		catch (SQLException e)
		{
			LOG.error(e.getMessage(), e);
		}

		// inserts data //
		try
		{
			this.update("INSERT INTO Categories(id, name) VALUES(1, 'Jan')");
			this.update("INSERT INTO Categories(id, name) VALUES(2, 'Feb')");
			this.update("INSERT INTO Categories(id, name) VALUES(3, 'Mar')");
			this.update("INSERT INTO Categories(id, name) VALUES(4, 'Apr')");
			this.update("INSERT INTO Categories(id, name) VALUES(5, 'May')");
			this.update("INSERT INTO Categories(id, name) VALUES(6, 'Jun')");
			this.update("INSERT INTO Categories(id, name) VALUES(7, 'Jul')");
			this.update("INSERT INTO Categories(id, name) VALUES(8, 'Aug')");
			this.update("INSERT INTO Categories(id, name) VALUES(9, 'Sep')");
			this.update("INSERT INTO Categories(id, name) VALUES(10, 'Oct')");
			this.update("INSERT INTO Categories(id, name) VALUES(11, 'Nov')");
			this.update("INSERT INTO Categories(id, name) VALUES(12, 'Dec')");

			this.update("INSERT INTO Data(seriesId, categoryId, value) VALUES(1, 1, 45)");
			this.update("INSERT INTO Data(seriesId, categoryId, value) VALUES(1, 2, 54)");
			this.update("INSERT INTO Data(seriesId, categoryId, value) VALUES(1, 3, 96)");
			this.update("INSERT INTO Data(seriesId, categoryId, value) VALUES(1, 4, 52)");
			this.update("INSERT INTO Data(seriesId, categoryId, value) VALUES(1, 5, 15)");
			this.update("INSERT INTO Data(seriesId, categoryId, value) VALUES(1, 6, 66)");
			this.update("INSERT INTO Data(seriesId, categoryId, value) VALUES(1, 7, 44)");
			this.update("INSERT INTO Data(seriesId, categoryId, value) VALUES(1, 8, 85)");
			this.update("INSERT INTO Data(seriesId, categoryId, value) VALUES(1, 9, 35)");
			this.update("INSERT INTO Data(seriesId, categoryId, value) VALUES(1, 10, 95)");

			this.update("INSERT INTO Data(seriesId, categoryId, value) VALUES(2, 1, 15)");
			this.update("INSERT INTO Data(seriesId, categoryId, value) VALUES(2, 2, 65)");
			this.update("INSERT INTO Data(seriesId, categoryId, value) VALUES(2, 3, 25)");
			this.update("INSERT INTO Data(seriesId, categoryId, value) VALUES(2, 4, 78)");
			this.update("INSERT INTO Data(seriesId, categoryId, value) VALUES(2, 5, 12)");
			this.update("INSERT INTO Data(seriesId, categoryId, value) VALUES(2, 6, 89)");
			this.update("INSERT INTO Data(seriesId, categoryId, value) VALUES(2, 7, 26)");
			this.update("INSERT INTO Data(seriesId, categoryId, value) VALUES(2, 8, 75)");
			this.update("INSERT INTO Data(seriesId, categoryId, value) VALUES(2, 9, 65)");
			this.update("INSERT INTO Data(seriesId, categoryId, value) VALUES(2, 10, 35)");
		}
		catch (SQLException e)
		{
			LOG.error(e.getMessage(), e);
		}

		return true;
	}

	void close()
	{
		if (this.connection != null)
		{
			try
			{
				if (!this.connection.isClosed())
				{
					Statement statement = this.connection.createStatement();
					statement.execute("SHUTDOWN");

					this.connection.close();
				}
			}
			catch (SQLException e)
			{
				LOG.error(e.getMessage(), e);
			}
		}
	}

	void update(String expression) throws SQLException
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
