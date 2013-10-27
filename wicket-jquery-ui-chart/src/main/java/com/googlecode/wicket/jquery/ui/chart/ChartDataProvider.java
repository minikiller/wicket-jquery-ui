package com.googlecode.wicket.jquery.ui.chart;

import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;

//TODO to remove
@Deprecated
public class ChartDataProvider implements IDataProvider<List<String>>
{
	private static final long serialVersionUID = 1L;

	@Override
	public Iterator<? extends List<String>> iterator(long first, long count)
	{
		return null;
	}

	@Override
	public long size()
	{
		return 0;
	}

	@Override
	public IModel<List<String>> model(List<String> object)
	{
		return null;
	}

	@Override
	public void detach()
	{
	}
}
