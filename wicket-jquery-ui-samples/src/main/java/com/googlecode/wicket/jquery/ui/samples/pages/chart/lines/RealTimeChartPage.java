package com.googlecode.wicket.jquery.ui.samples.pages.chart.lines;

import org.apache.wicket.ajax.AjaxRequestTarget;

import com.googlecode.wicket.jquery.ui.chart.Gallery;
import com.googlecode.wicket.jquery.ui.chart.realtime.RealTimeChart;
import com.googlecode.wicket.jquery.ui.chart.realtime.RealTimeMode;

public class RealTimeChartPage extends AbstractChartPage
{
	private static final long serialVersionUID = 1L;

	public RealTimeChartPage()
	{
		// Chart //
		RealTimeChart chart = new RealTimeChart("chart", Gallery.Lines) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onInterval(AjaxRequestTarget target, int sequence)
			{
//				this.setValue(Math.random() * 100);
				this.setValues(25 + Math.random() * 25, Math.random() * 25);
			}
		};

		chart.getRealTimeSettings().setMode(RealTimeMode.Loop);
//		chart.getRealTimeSettings().setBufferSize(15);

		this.add(chart);
	}
}
