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
package com.googlecode.wicket.kendo.ui.widget.notification;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import com.googlecode.wicket.jquery.core.IJQueryWidget;
import com.googlecode.wicket.jquery.core.JQueryBehavior;
import com.googlecode.wicket.jquery.core.Options;

/**
 * Provides a {@link FeedbackPanel} customized with the jQuery theme TODO javadoc
 *
 * @author Sebastien Briquet - sebfz1
 *
 */
public class AjaxNotification extends WebMarkupContainer implements IJQueryWidget
{
	private static final long serialVersionUID = 1L;

	private static final String INFO = "info";
	private static final String SUCCESS = "success";
	private static final String WARNING = "warning";
	private static final String ERROR = "error";

	private Options options;
	private NotificationBehavior widgetBehavior = null;

	/**
	 * Constructor
	 *
	 * @param id the markup id
	 */
	public AjaxNotification(String id)
	{
		this(id, new Options());
	}

	/**
	 * Constructor
	 *
	 * @param id the markup id
	 * @param options the {@link Options}
	 */
	public AjaxNotification(String id, Options options)
	{
		super(id);

		this.options = options;
	}

	// Events //

	@Override
	protected void onInitialize()
	{
		super.onInitialize();

		this.add(this.widgetBehavior = this.newWidgetBehavior(JQueryWidget.getSelector(this)));  //cannot be in ctor as the markupId may be set manually afterward
	}

	@Override
	public void onConfigure(JQueryBehavior behavior)
	{
		// noop
	}

	@Override
	public void onBeforeRender(JQueryBehavior behavior)
	{
		// noop
	}

	// Methods //

	public void show(AjaxRequestTarget target, String message, String level)
	{
		this.widgetBehavior.show(target, message, level);
	}

	public void hide(AjaxRequestTarget target)
	{
		this.widgetBehavior.hide(target);
	}

	public void info(AjaxRequestTarget target, String message)
	{
		this.show(target, message, INFO);
	}

	public void success(AjaxRequestTarget target, String message)
	{
		this.show(target, message, SUCCESS);
	}

	public void warn(AjaxRequestTarget target, String message)
	{
		this.show(target, message, WARNING);
	}

	public void error(AjaxRequestTarget target, String message)
	{
		this.show(target, message, ERROR);
	}

	// IJQueryWidget //

	@Override
	public NotificationBehavior newWidgetBehavior(String selector)
	{
		return new NotificationBehavior(selector, this.options) {

			private static final long serialVersionUID = 1L;

		};
	}
}
