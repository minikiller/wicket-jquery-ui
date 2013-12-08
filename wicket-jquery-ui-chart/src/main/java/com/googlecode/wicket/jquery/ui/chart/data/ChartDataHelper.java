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

import java.util.Collection;
import java.util.List;

import org.apache.wicket.util.string.Strings;

/**
 * Provides a {@link ChartData} helper
 *
 * @author Sebastien Briquet - sebfz1
 */
public class ChartDataHelper implements IChartData
{
	/**
	 * Adds a value to a {@link ChartData} identified by its category<br/>
	 * If the category is not found within the {@link ChartData} list, a new {@link ChartData} is created and added.
	 *
	 * @param list the list of {@link ChartData}
	 * @param category the category the value belongs to
	 * @param value the value
	 * @return <tt>true</tt> (as specified by {@link Collection#add})
	 */
	public static boolean add(List<ChartData> list, String category, Number value)
	{
		for (ChartData data : list)
		{
			if (Strings.isEqual(category, data.getCategory()))
			{
				return data.add(value);
			}
		}

		return list.add(new ChartData(category, value));
	}

	/**
	 * Helper class
	 */
	private ChartDataHelper()
	{
	}
}
