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
package com.googlecode.wicket.jquery.ui.chart;

import java.util.List;

import org.apache.wicket.model.LoadableDetachableModel;

import com.googlecode.wicket.jquery.ui.chart.data.IChartData;

/**
 *
 * @author Sebastien Briquet - sebfz1
 * @param <T>
 *
 */
public abstract class ChartModel<T extends IChartData> extends LoadableDetachableModel<List<T>>
{
	private static final long serialVersionUID = 1L;

	static <T extends IChartData> String toJson(ChartModel<T> model)
	{
		StringBuilder builder = new StringBuilder("[ ");

		List<T> list = model.getObject(); // calls #load()

		for (int i = 0; i < list.size(); i++)
		{
			if (i > 0)
			{
				builder.append(", ");
			}

			ChartModel.toJson(builder, list.get(i));
		}

		builder.append(" ]");

		return builder.toString();
	}

	static <T extends IChartData> void toJson(StringBuilder builder, T bean)
	{
		bean.toJson(builder);
	}

	/**
	 * Constructor
	 */
	public ChartModel()
	{
	}
}
