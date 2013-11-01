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

import com.googlecode.wicket.jquery.core.Options;

/**
 *
 * @author Sebastien Briquet - sebfz1
 *
 */
public class Series
{
	public static String toJson(List<Series> list)
	{
		StringBuilder builder = new StringBuilder("[ ");

		for (int i = 0; i < list.size(); i++)
		{
			if (i > 0)
			{
				builder.append(", ");
			}

			Series.toJson(builder, list.get(i));
		}

		builder.append(" ]");

		return builder.toString();
	}

	public static void toJson(StringBuilder builder, Series series)
	{
		series.toJson(builder);
	}

	private String name;
	private Gallery gallery;

	public Series(String name)
	{
		this(null, name);
	}

	/**
	 * Constructor
	 * @param gallery
	 * @param name
	 */
	public Series(Gallery gallery, String name)
	{
		this.name = name;
		this.gallery = gallery;
	}

	public Gallery getGallery()
	{
		return this.gallery;
	}

	public String getName()
	{
		return this.name;
	}

	protected CharSequence toJson(StringBuilder builder)
	{
		builder.append("{ ");

		if (this.getGallery() != null)
		{
			builder.append(Options.QUOTE).append("gallery").append(Options.QUOTE).append(": ");
			builder.append(this.getGallery());
			builder.append(", ");
		}

		builder.append(Options.QUOTE).append("text").append(Options.QUOTE).append(": ");
		builder.append(Options.QUOTE).append(this.getName()).append(Options.QUOTE);

		builder.append(" }");

		return builder;
	}
}
