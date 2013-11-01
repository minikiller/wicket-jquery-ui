package com.googlecode.wicket.jquery.ui.chart.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Provides a dataset bean typically used for simple charts

 *
 */
//TODO to remove
@Deprecated
public class SeriesDataset
{
	protected final List<Serializable> series;
	protected final List<Serializable> values;

	public SeriesDataset()
	{
		this.series = new ArrayList<Serializable>();
		this.values = new ArrayList<Serializable>();
	}

	public SeriesDataset(int count)
	{
		this.series = new ArrayList<Serializable>(count);
		this.values = new ArrayList<Serializable>(count);
	}

	public void add(Serializable serie, Serializable value)
	{
		this.series.add(serie);
		this.values.add(value);
	}
//
//	/**
//	 * ResultSet should have the following structure information:<br/>
//	 * <code>[Serie, Value]</code><br/>
//	 * The above names are not mandatory, only their positions.
//	 * @param rs
//	 * @throws PersistenceException
//	 */
//	@Override
//	public void fill(ResultSetResource rs) throws PersistenceException
//	{
//		if (rs != null)
//		{
//			while (rs.next())
//			{
//				this.series.add(rs.getString(1));
//				this.values.add(rs.getInt(2));
//			}
//		}
//	}

	public int count()
	{
		return this.values.size();
	}

	public Serializable getSerie(int index)
	{
		return this.series.get(index);
	}

	public Serializable getValue(int index)
	{
		return this.values.get(index);
	}

	public List<Serializable> getSeries()
	{
		return Collections.unmodifiableList(this.series);
	}

	public List<Serializable> getValues()
	{
		return Collections.unmodifiableList(this.values);
	}
}
