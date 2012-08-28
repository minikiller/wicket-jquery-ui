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
package com.googlecode.wicket.jquery.ui.dialog;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.model.IModel;

import com.googlecode.wicket.jquery.ui.IJQueryWidget;
import com.googlecode.wicket.jquery.ui.JQueryBehavior;
import com.googlecode.wicket.jquery.ui.JQueryEvent;
import com.googlecode.wicket.jquery.ui.JQueryPanel;
import com.googlecode.wicket.jquery.ui.Options;
import com.googlecode.wicket.jquery.ui.ajax.JQueryAjaxBehavior;

/**
 * Base class for implementing jQuery dialogs
 * @author Sebastien Briquet - sebfz1
 *
 * @param <T> the type of the model object
 */
public abstract class AbstractDialog<T extends Serializable> extends JQueryPanel
{
	private static final long serialVersionUID = 1L;
	private static final String METHOD = "dialog";

	/* Default Button labels */
	public static final String LBL_OK = "OK";
	public static final String LBL_NO = "No";
	public static final String LBL_YES = "Yes";
	public static final String LBL_CLOSE = "Close";
	public static final String LBL_CANCEL = "Cancel";
	public static final String LBL_SUBMIT = "Submit";

	/** Default width */
	private static final int WIDTH = 450;

	private String title;
	private boolean modal;
	private JQueryBehavior widgetBehavior;

	/**
	 * Default button
	 */
	private final DialogButton btnOk = new DialogButton(LBL_OK);

	/**
	 * @param id the markupId, an html div suffice to host a dialog.
	 * @param title the title of the dialog
	 */
	public AbstractDialog(String id, String title)
	{
		this(id, title, null, true);
	}

	/**
	 * @param id the markupId, an html div suffice to host a dialog.
	 * @param title the title of the dialog
	 * @param model the model to be used in the dialog. It is retransmitted to the {@link DialogEvent} object.
	 */
	public AbstractDialog(String id, String title, IModel<T> model)
	{
		this(id, title, model, true);
	}

	/**
	 * @param id the markupId, an html div suffice to host a dialog.
	 * @param title the title of the dialog
	 * @param modal indicates whether the dialog is modal
	 */
	public AbstractDialog(String id, String title, boolean modal)
	{
		this(id, title, null, modal);
	}

	/**
	 * @param id markupId, an html div suffice to host a dialog.
	 * @param title the title of the dialog
	 * @param modal indicates whether the dialog is modal
	 * @param model the model to be used in the dialog; it is retransmitted to the {@link DialogEvent} object.
	 */
	public AbstractDialog(String id, String title, IModel<T> model, boolean modal)
	{
		super(id, model);

		this.title = title;
		this.modal = modal;

		this.setOutputMarkupPlaceholderTag(true);
	}


	/* Events */
	@Override
	protected void onInitialize()
	{
		super.onInitialize();

		for (DialogButton button : this.getButtons())
		{
			this.add(this.newButtonAjaxBehavior(button));
		}

		this.add(this.widgetBehavior = this.newWidgetBehavior(JQueryWidget.getSelector(this))); //warning: ButtonAjaxBehavior(s) should be sets at this point as there are used on bind()
	}


	/**
	 * DO NOT OVERRIDE UNLESS A VERY GOOD REASON
	 */
	@Override
	public void onEvent(IEvent<?> event)
	{
		if (event.getPayload() instanceof DialogEvent)
		{
			this.onClick((DialogEvent) event.getPayload());
		}
	}

	/**
	 * Triggered when the dialog opens
	 * @param target
	 */
	protected void onOpen(AjaxRequestTarget target)
	{

	}

	/**
	 * Triggered when a button is clicked.
	 * This method may be overridden to handle button behaviors, but the dialog will not been closed until <code>super.onClick(event)</code> or {@link #close(AjaxRequestTarget, DialogButton)} is called.
	 * @param event {@link DialogEvent}
	 */
	protected void onClick(DialogEvent event)
	{
		this.close(event.getTarget(), event.getButton());
	}

	/**
	 * Triggered when the dialog closes.
	 * @param target the {@link AjaxRequestTarget}
	 * @param button the button that closed the dialog
	 */
	protected abstract void onClose(AjaxRequestTarget target, DialogButton button);


	// Properties //
	/**
	 * Gets the dialog's buttons.<br/>
	 * It is allowed to return a predefined list (ie: DialogButtons#OK_CANCEL#toList()) as long as the buttons state (enable and/or visible) are not modified<br/>
	 * <b>Warning: </b>It is not legal to create the buttons to be returned in this method.
	 * @return {@link #btnOk} by default
	 */
	protected List<DialogButton> getButtons()
	{
		return Arrays.asList(this.btnOk);
	}

	/**
	 * Gets the dialog's with
	 * @return {@link #WIDTH} by default
	 */
	public int getWidth()
	{
		return AbstractDialog.WIDTH;
	}

	/**
	 * Gets the dialog's title
	 * @return the title supplied in the constructor by default
	 */
	public String getTitle()
	{
		return this.title;
	}

	/**
	 * Gets the modal flag
	 * @return the modal flag supplied in the constructor by default
	 */
	public boolean isModal()
	{
		return this.modal;
	}

	/**
	 * Gets the resizable flag
	 * @return false by default
	 */
	public boolean isResizable()
	{
		return false;
	}

	/**
	 * Gets the model
	 * @return the parameterized model
	 */
	@SuppressWarnings("unchecked")
	public IModel<T> getModel()
	{
		return (IModel<T>)this.getDefaultModel();
	}

	/**
	 * Gets the model object
	 * @return the typed model object
	 */
	@SuppressWarnings("unchecked")
	public T getModelObject()
	{
		return (T)this.getDefaultModelObject();
	}

	/**
	 * Sets the model object
	 * @param object the typed model object
	 */
	public void setModelObject(T object)
	{
		this.setDefaultModelObject(object);
	}

	// Methods //
	/**
	 * Finds a {@link DialogButton} - identified by its text - within the list of buttons returned by {@link #getButtons()}
	 * @param text the button's text
	 * @return the {@link DialogButton} if found, null otherwise
	 */
	public DialogButton findButton(String text)
	{
		for (DialogButton button : this.getButtons())
		{
			if (button != null && button.equals(text))
			{
				return button;
			}
		}

		return null;
	}


	// IJQueryWidget //
	/**
	 * @see IJQueryWidget#newWidgetBehavior(String)
	 */
	@Override
	public JQueryBehavior newWidgetBehavior(String selector)
	{
		return new JQueryBehavior(selector, METHOD) {

			private static final long serialVersionUID = 1L;

			@Override
			public void onConfigure(Component component)
			{
				// immutable options //
				this.setOption("autoOpen", false);
				this.setOption("title", Options.asString(AbstractDialog.this.title));
				this.setOption("modal", AbstractDialog.this.modal);
				this.setOption("resizable", AbstractDialog.this.isResizable());
				this.setOption("width", AbstractDialog.this.getWidth());

				// buttons events //
				StringBuffer buttons = new StringBuffer("[ ");

				int index = 0;
				for(ButtonAjaxBehavior behavior : AbstractDialog.this.getBehaviors(ButtonAjaxBehavior.class))
				{
					DialogButton button = behavior.getButton();

					if (index++ > 0) { buttons.append(", "); }
					buttons.append("{");
					buttons.append("'id': '").append(button.getMarkupId()).append("', ");
					buttons.append("'text': '").append(button.toString()).append("', ");
					if (!button.isEnabled()) { buttons.append("'disabled': true, "); }
					buttons.append("'click': function() { ").append(behavior.getCallbackScript()).append(" }");
					buttons.append("}");
				}

				buttons.append(" ]");

				this.setOption("buttons", buttons);
			}
		};
	}

	/**
	 * Gets a new ButtonAjaxBehavior that will be called by the corresponding dialog's button.<br/>
	 * This method mays be overridden internally to provide another behavior; ButtonAjaxBehavior has package visibility
	 *
	 * @param button the button that is passed to the behavior so it can be retrieved via the {@link DialogEvent}
	 */
	protected ButtonAjaxBehavior newButtonAjaxBehavior(DialogButton button)
	{
		return new ButtonAjaxBehavior(this, button);
	}

	// Methods //
	/**
	 * Opens the dialogs in ajax.<br/>
	 * It triggers the  {@link #onOpen(AjaxRequestTarget)} event
	 * @param target the {@link AjaxRequestTarget}
	 */
	public final void open(AjaxRequestTarget target)
	{
		this.onOpen(target);

		if (this.widgetBehavior != null)
		{
			target.appendJavaScript(this.widgetBehavior.$("'open'"));
		}
	}

	/**
	 * Closes the dialogs in ajax.<br/>
	 * It triggers the  {@link #onClose(AjaxRequestTarget, DialogButton)} event
	 * @param target the {@link AjaxRequestTarget}
	 * @param button the button that closes the dialog
	 */
	public final void close(AjaxRequestTarget target, DialogButton button)
	{
		if (this.widgetBehavior != null)
		{
			target.appendJavaScript(this.widgetBehavior.$("'close'"));
		}

		this.onClose(target, button);
	}


	// Ajax behavior //
	/**
	 * Provides the {@link JQueryAjaxBehavior} being called by the button(s).
	 *
	 * @author Sebastien Briquet - sebfz1
	 *
	 */
	class ButtonAjaxBehavior extends JQueryAjaxBehavior
	{
		private static final long serialVersionUID = 1L;

		private final DialogButton button;

		public ButtonAjaxBehavior(AbstractDialog<T> dialog, DialogButton button)
		{
			super(dialog);

			this.button = button;
		}

		public DialogButton getButton()
		{
			return this.button;
		}

		@Override
		protected JQueryEvent newEvent(AjaxRequestTarget target)
		{
			return new DialogEvent(target, this.button);
		}
	}
}
