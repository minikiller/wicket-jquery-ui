package com.googlecode.wicket.jquery.ui.chart;

import java.util.Collections;
import java.util.List;

import org.apache.wicket.model.IModel;

public abstract class ChartDataProvider // implements IDataProvider<List<String>>
{
	private static final long serialVersionUID = 1L;

	public IModel<List<String>> model(List<String> object)
	{
		return null;
	}

	@Override
	public void detach()
	{
		// noop
	}

	public List<Series> getSeries()
	{
		return Collections.emptyList(); //TODO: mark abstract?
	}
}
