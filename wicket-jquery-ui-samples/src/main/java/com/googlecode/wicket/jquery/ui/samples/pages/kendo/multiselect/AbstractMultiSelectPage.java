package com.googlecode.wicket.jquery.ui.samples.pages.kendo.multiselect;

import java.util.Arrays;
import java.util.List;

import com.googlecode.wicket.jquery.ui.samples.pages.kendo.AbstractKendoPage;



abstract class AbstractMultiSelectPage extends AbstractKendoPage
{
	private static final long serialVersionUID = 1L;

	public AbstractMultiSelectPage()
	{
	}

	@Override
	protected List<DemoLink> getDemoLinks()
	{
		return Arrays.asList(
				new DemoLink(DefaultMultiSelectPage.class, "MultiSelect"),
				new DemoLink(LazyMultiSelectPage.class, "MultiSelect: lazy load")
			);
	}
}
