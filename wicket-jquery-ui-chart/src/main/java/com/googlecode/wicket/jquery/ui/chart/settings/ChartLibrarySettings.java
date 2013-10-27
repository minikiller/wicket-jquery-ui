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
package com.googlecode.wicket.jquery.ui.chart.settings;

import org.apache.wicket.request.resource.ResourceReference;

import com.googlecode.wicket.jquery.core.settings.JQueryLibrarySettings;
import com.googlecode.wicket.jquery.ui.chart.resource.ChartJavaScriptResourceReference;
import com.googlecode.wicket.jquery.ui.chart.resource.ChartStyleSheetResourceReference;

/**
 * Default implementation of {@link IChartLibrarySettings}.<br/>
 *
 * @author Sebastien Briquet - sebfz1
 *
 */
public class ChartLibrarySettings extends JQueryLibrarySettings implements IChartLibrarySettings
{
	private static ChartLibrarySettings instance = null;

	/**
	 * INTERNAL USE<br/>
	 * Gets the {@link ChartLibrarySettings} instance
	 * @return the {@link ChartLibrarySettings} instance
	 */
	public static synchronized ChartLibrarySettings get()
	{
		if (ChartLibrarySettings.instance == null)
		{
			ChartLibrarySettings.instance = new ChartLibrarySettings();
		}

		return ChartLibrarySettings.instance;
	}


	/**
	 * Constructor
	 */
	protected ChartLibrarySettings()
	{
	}

	@Override
	public ResourceReference getChartStyleSheetReference()
	{
		return ChartStyleSheetResourceReference.get();
	}


	@Override
	public ResourceReference getChartJavaScriptReference()
	{
		return ChartJavaScriptResourceReference.get();
	}
}
