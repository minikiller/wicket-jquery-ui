/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.googlecode.wicket.jquery.ui.chart.data;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.wicket.jquery.ui.chart.ChartModel;

/**
 *
 * @author Sebastien Briquet - sebfz1
 * @param <T>
 *
 */
public abstract class SqlChartModel<T extends IChartData> extends ChartModel<T> implements IChartDataset
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 */
	public SqlChartModel()
	{
	}

	@Override
	protected List<T> load()
	{
		List<CategoryData> list = new ArrayList<CategoryData>();

		try
		{
			ResultSet rs = this.newResultSet();

			ResultSetMetaData meta = rs.getMetaData();
			int cols = meta.getColumnCount();

			if (cols > 1)
			{
				while (rs.next())
				{
					String category = rs.getString(1);
					Number[] values = new Number[cols - 1];

					for (int i = 1; i < cols; i++)
					{
						values[i] = rs.getInt(i + 1);
					}

					list.add(new CategoryData(category, values));
				}
			}
		}
		catch (SQLException ex)
		{
			this.onSqlException(ex);
		}

		return list;
	}

	/**
	 * @param ex
	 */
	protected void onSqlException(SQLException ex)
	{
		// noop
	}

	protected abstract ResultSet newResultSet();
}
