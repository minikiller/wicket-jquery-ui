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

import org.apache.wicket.util.io.IClusterable;

import com.googlecode.wicket.jquery.core.Options;

/**
 * Provides a series object<br/>
 * A series has is identified by a name and can use its own gallery (overrides the defined {@link Chart} gallery)
 *
 * @author Sebastien Briquet - sebfz1
 */
public class Series implements IClusterable
{
	private static final long serialVersionUID = 1L;

	private String name;
	private Gallery gallery;

	/**
	 * Constructor
	 *
	 * @param name the series name
	 */
	public Series(String name)
	{
		this(name, Gallery.None);
	}

	/**
	 * Constructor
	 *
	 * @param name the series name
	 * @param gallery the {@link Gallery} to use
	 */
	public Series(String name, Gallery gallery)
	{
		this.name = name;
		this.gallery = gallery;
	}

	/**
	 * Gets the series name
	 *
	 * @return the series name
	 */
	public String getName()
	{
		return this.name;
	}

	/**
	 * Gets the gallery
	 *
	 * @return the gallery
	 */
	public Gallery getGallery()
	{
		return this.gallery;
	}

	/**
	 * Gets the JSON representation of this {@link Series}
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder("{ ");

		if (this.getGallery() != Gallery.None)
		{
			builder.append(Options.QUOTE).append("gallery").append(Options.QUOTE).append(": ");
			builder.append(this.getGallery());
			builder.append(", ");
		}

		builder.append(Options.QUOTE).append("text").append(Options.QUOTE).append(": ");
		builder.append(Options.QUOTE).append(this.getName()).append(Options.QUOTE);

		return builder.append(" }").toString();
	}
}
