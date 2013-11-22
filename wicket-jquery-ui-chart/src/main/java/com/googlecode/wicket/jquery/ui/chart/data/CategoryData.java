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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.googlecode.wicket.jquery.core.Options;

/**
 * Provides a category/points data<br/>
 * Values should be inserted in the same order of declared series.
 *
 * @author Sebastien Briquet - sebfz1
 */
public class CategoryData implements IChartData
{
	private static final DateFormat ISO8601_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

	private final String category;
	private final Number[] values;

	/**
	 * @param category could be a ISO 8601 date string (yyyy-MM-ddTHH:mm:ss.fffZ)
	 * @param values the series values
	 */
	public CategoryData(String category, Number... values)
	{
		this.category = category;
		this.values = values;
	}

	/**
	 * @param category the {@link Date}
	 * @param values the series values
	 */
	public CategoryData(Date category, Number... values)
	{
		this.category = ISO8601_FORMAT.format(category);
		this.values = values;
	}

	public String getCategory()
	{
		return this.category;
	}

	public Number[] getValues()
	{
		return this.values;
	}

	@Override
	public void toJson(StringBuilder builder)
	{
		builder.append("{ ");
		builder.append(Options.QUOTE).append("category").append(Options.QUOTE).append(": ");
		builder.append(Options.QUOTE).append(this.getCategory()).append(Options.QUOTE);

		Number[] values = this.getValues();

		for (int i = 0; i < values.length; i++)
		{
			Number value = values[i];

			builder.append(", ");
			builder.append(Options.QUOTE).append("s").append(i).append(Options.QUOTE).append(": ");
			builder.append(value);
		}

		builder.append(" }");
	}
}
