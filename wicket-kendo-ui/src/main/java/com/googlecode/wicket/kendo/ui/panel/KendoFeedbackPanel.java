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
package com.googlecode.wicket.kendo.ui.panel;

import org.apache.wicket.Component;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.FeedbackMessagesModel;
import org.apache.wicket.feedback.IFeedback;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import com.googlecode.wicket.jquery.core.IJQueryWidget;
import com.googlecode.wicket.jquery.core.JQueryBehavior;
import com.googlecode.wicket.jquery.core.Options;
import com.googlecode.wicket.kendo.ui.widget.notification.NotificationBehavior;

/**
 * Provides a {@link FeedbackPanel} customized with the jQuery theme TODO javadoc
 *
 * @author Sebastien Briquet - sebfz1
 *
 */
public class KendoFeedbackPanel extends WebMarkupContainer implements IJQueryWidget, IFeedback
{
	private static final long serialVersionUID = 1L;

	private Options options;
	private FeedbackMessagesModel feedbackMessagesModel;

	/**
	 * Constructor
	 *
	 * @param id the markup id
	 */
	public KendoFeedbackPanel(String id)
	{
		this(id, new Options());
	}

	/**
	 * Constructor
	 *
	 * @param id the markup id
	 * @param options the {@link Options}
	 */
	public KendoFeedbackPanel(String id, Options options)
	{
		super(id);

		this.options = options;
	}

	// Events //

	@Override
	protected void onInitialize()
	{
		super.onInitialize();

		this.feedbackMessagesModel = this.newFeedbackMessagesModel();
		this.add(JQueryWidget.newWidgetBehavior(this));
	}

	@Override
	public void onConfigure(JQueryBehavior behavior)
	{
		behavior.setOption("hideOnClick", false);
		behavior.setOption("autoHideAfter", 0);
	}

	@Override
	public void onBeforeRender(JQueryBehavior behavior)
	{
		// noop
	}

	@Override
	protected void onDetach()
	{
		super.onDetach();

		this.feedbackMessagesModel.detach();
	}

	// IJQueryWidget //

	@Override
	public NotificationBehavior newWidgetBehavior(String selector)
	{
		return new NotificationBehavior(selector, this.options) {

			private static final long serialVersionUID = 1L;

			@Override
			public void onConfigure(Component component)
			{
				super.onConfigure(component);

				this.setOption("appendTo", Options.asString(this.selector));
			}

			@Override
			protected String $()
			{
				StringBuilder builder = new StringBuilder(super.$());

				for (FeedbackMessage message : feedbackMessagesModel.getObject())
				{
					builder.append(this.$(message.getMessage(), message.getLevelAsString().toLowerCase()));

					message.markRendered();
				}

				return builder.toString();
			}
		};
	}

	// Factories //

	/**
	 * Gets a new instance of the FeedbackMessagesModel to use.<br/>
	 * This method can be overridden to provide a {@link IFeedbackMessageFilter}
	 *
	 * @return a new {@link FeedbackMessagesModel}
	 */
	protected FeedbackMessagesModel newFeedbackMessagesModel()
	{
		return new FeedbackMessagesModel(this);
	}
}
