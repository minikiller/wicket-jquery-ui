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

/**
 *
 * @author Sebastien Briquet - sebfz1
 *
 */
public enum Gallery
{
	None("cfx.Gallery.None"),
	Lines("cfx.Gallery.Lines"),
	Bar("cfx.Gallery.Bar"),
	Area("cfx.Gallery.Area"),
	Scatter("cfx.Gallery.Scatter"),
	Pie("cfx.Gallery.Pie"),
	Curve("cfx.Gallery.Curve"),
	Pareto("cfx.Gallery.Pareto"),
	Step("cfx.Gallery.Step"),
	HighLowClose("cfx.Gallery.HighLowClose"),
	Surface("cfx.Gallery.Surface"),
	Radar("cfx.Gallery.Radar"),
	Polar("cfx.Gallery.Polar"),
	Cube("cfx.Gallery.Cube"),
	Doughnut("cfx.Gallery.Doughnut"),
	Pyramid("cfx.Gallery.Pyramid"),
	Bubble("cfx.Gallery.Bubble"),
	OpenHighLowClose("cfx.Gallery.OpenHighLowClose"),
	Candlestick("cfx.Gallery.Candlestick"),
	Contour("cfx.Gallery.Contour"),
	CurveArea("cfx.Gallery.CurveArea"),
	Gantt("cfx.Gallery.Gantt");

	private String gallery;

	Gallery(String cfx)
	{
		this.gallery = cfx;
	}

	@Override
	public String toString()
	{
		return this.gallery;
	}
}
