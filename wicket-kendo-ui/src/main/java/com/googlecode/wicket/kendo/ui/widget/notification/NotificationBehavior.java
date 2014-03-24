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

import java.io.Serializable;

import org.apache.wicket.ajax.AjaxRequestTarget;

import com.googlecode.wicket.jquery.core.JQueryBehavior;
import com.googlecode.wicket.jquery.core.Options;
import com.googlecode.wicket.kendo.ui.KendoAbstractBehavior;

/**
 * Provides a {@link FeedbackPanel} customized with the jQuery theme
 * TODO javadoc
 *
 * @author Sebastien Briquet - sebfz1
 *
 */
/**
 * Provides a jQuery button {@link JQueryBehavior}
 */
public class NotificationBehavior extends KendoAbstractBehavior
{
	private static final long serialVersionUID = 1L;
	private static final String METHOD = "kendoNotification";

	public NotificationBehavior(String selector)
	{
		super(selector, METHOD);
	}

	public NotificationBehavior(String selector, Options options)
	{
		super(selector, METHOD, options);
	}

	// Methods //

	/**
	 * Formats the message (escaping, etc)
	 *
	 * @param message the message to format
	 * @param level TODO javadoc
	 * @return the formated message
	 */
	protected String format(Serializable message, String level)
	{
		return String.valueOf(message).replace("'", "\\'");
	}

	/**
	 * TODO javadoc
	 * @param target
	 * @param message
	 * @param level
	 */
	public void show(AjaxRequestTarget target, Serializable message, String level)
	{
		target.appendJavaScript(this.$(message, level));
	}

	public void hide(AjaxRequestTarget target)
	{
		target.appendJavaScript(String.format("jQuery('%s').data('%s').hide();", this.selector, METHOD));
	}

	/**
	 * Gets the jQuery statement that logs the message<br/>
	 * <b>Warning: </b> This method is *not* called by the behavior directly (only {@link #$()} is).
	 *
	 * @param message the message to log
	 * @param error indicates whether the message is an error message
	 * @return the jQuery statement
	 */
	protected String $(Serializable message, String level)
	{
		return String.format("jQuery('%s').data('%s').show('%s', '%s');", this.selector, METHOD, this.format(message, level), level);
	}
}
