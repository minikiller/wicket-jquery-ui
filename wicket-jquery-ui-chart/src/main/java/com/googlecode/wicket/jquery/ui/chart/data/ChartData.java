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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.googlecode.wicket.jquery.core.Options;

/**
 * Provides data the for chart model<br/>
 * Values should be inserted in the same order of declared series.
 *
 * @author Sebastien Briquet - sebfz1
 */
public class ChartData implements IChartData
{
	private static final DateFormat ISO8601_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

	private final String category;
	private final List<Number> values;

	/**
	 * Constructor
	 *
	 * @param category the category; can be an ISO 8601 date (yyyy-MM-ddTHH:mm:ss.fffZ)
	 * @param values the series values
	 */
	public ChartData(String category, Number... values)
	{
		this.category = category;
		this.values = new ArrayList<Number>(Arrays.asList(values));
	}

	/**
	 * Constructor
	 *
	 * @param category the {@link Date}
	 * @param values the series values
	 */
	public ChartData(Date category, Number... values)
	{
		this.category = ISO8601_FORMAT.format(category);
		this.values = new ArrayList<Number>(Arrays.asList(values));
	}

	/**
	 * Gets the category
	 *
	 * @return the category
	 */
	public String getCategory()
	{
		return this.category;
	}

	/**
	 * Gets the values (immutable)
	 *
	 * @return the values
	 */
	public List<Number> getValues()
	{
		return Collections.unmodifiableList(this.values);
	}

	/**
	 * Adds a value to the list of values hold by this {@link ChartData}
	 *
	 * @param value the value
	 * @return <tt>true</tt> (as specified by {@link Collection#add})
	 */
	public boolean add(Number value)
	{
		return this.values.add(value);
	}

	/**
	 * Gets the JSON representation of this {@link ChartData}
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder("{ ");

		builder.append(Options.QUOTE).append("category").append(Options.QUOTE).append(": ");
		builder.append(Options.QUOTE).append(this.getCategory()).append(Options.QUOTE);

		List<Number> values = this.getValues();

		for (int i = 0; i < values.size(); i++)
		{
			Number value = values.get(i);

			builder.append(", ");
			builder.append(Options.QUOTE).append("s").append(i).append(Options.QUOTE).append(": ");
			builder.append(value);
		}

		return builder.append(" }").toString();
	}
}
