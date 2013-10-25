package com.googlecode.wicket.jquery.ui.chart.data;

import java.io.Serializable;
import java.sql.ResultSet;

public interface IChartDataset extends Serializable
{
	void fill(ResultSet rs);
}
