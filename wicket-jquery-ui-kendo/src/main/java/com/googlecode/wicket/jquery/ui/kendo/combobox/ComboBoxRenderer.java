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
package com.googlecode.wicket.jquery.ui.kendo.combobox;

import org.apache.wicket.util.lang.PropertyResolver;

import com.googlecode.wicket.jquery.ui.renderer.TextRenderer;

/**
 * TODO javadoc
 * @author Sebastien Briquet - sebastien@7thweb.net
 *
 * @param <T>
 */
public class ComboBoxRenderer<T> extends TextRenderer<T> /* AbstractChoiceRenderer<T> /* implements IChoiceRenderer<T> */
{
	private static final long serialVersionUID = 1L;
	private static final String TEXT_FIELD = "cb_text"; 
	private static final String VALUE_FIELD = "cb_value";

	private String valueExpression = null; 

	public ComboBoxRenderer()
	{
		
	}
	
	public ComboBoxRenderer(String textExpression)
	{
		super(textExpression);
	}

	public ComboBoxRenderer(String textExpression, String valueExpression)
	{
		super(textExpression);
		
		this.valueExpression = valueExpression;
	}

	public String getTextField()
	{
		String testExpression = super.getExpression();

		if (testExpression != null)
		{
			return testExpression;
		}
		
		return TEXT_FIELD;
	}

	/**
	 * @param object 
	 * @return the index as String if the object is null or the property expression has not been found
	 * 
	 */
	public String getValue(T object)
	{
		if (this.valueExpression != null)
		{
			Object value = PropertyResolver.getValue(this.valueExpression, object); //if the object is null, null is returned

			if (value != null)
			{
				return value.toString();
			}
		}

		return this.getText(object);
	}

	public String getValueField()
	{
		if (this.valueExpression != null)
		{
			return this.valueExpression;
		}
		
		return VALUE_FIELD;
	}
} 