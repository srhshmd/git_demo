package com.example.testmemory;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.SeriesSelection;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;


public class KnowledgeAnysisActivity extends Activity{
	
	private List<DateScore> dates1 = new ArrayList<DateScore>();
	private List<DateScore> dates2 = new ArrayList<DateScore>();
	
	class DateScore{
		private String date;
		private int score;
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public int getScore() {
			return score;
		}
		public void setScore(int score) {
			this.score = score;
		}
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout layout = new LinearLayout(this);
//		LayoutParams param = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
//		param.leftMargin = 30;
//		View v = getChartView();
//		v.setPadding(30, 0, 0, 0);
		layout.addView(getChartView());
		
		setContentView(layout);
	}

	XYMultipleSeriesRenderer renderer;
	XYMultipleSeriesDataset dataset;
	private View getChartView(){
		View chart = null;
		dataset = getDateSet();
		renderer = getRenderer();
		GraphicalView graphicalView = ChartFactory.getLineChartView(this, dataset, renderer);
		
		graphicalView.setOnClickListener(new ChartViewClick());
		return graphicalView;
	}
	
	class ChartViewClick implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			GraphicalView graphicalView = (GraphicalView) v;

			SeriesSelection seriesSelection = graphicalView
					.getCurrentSeriesAndPoint();

			if (seriesSelection == null) {
				return;
			}
			XYSeriesRenderer xyRenderer = (XYSeriesRenderer) renderer.getSeriesRendererAt(seriesSelection.getSeriesIndex());
			
			renderer.removeSeriesRenderer(xyRenderer);
			xyRenderer.setPointStyle(PointStyle.SQUARE);
			renderer.addSeriesRenderer(seriesSelection.getSeriesIndex(), xyRenderer);
			
//			int index = seriesSelection.getSeriesIndex();
//			dataset.removeSeries(index);
//			dataset.addSeries(index, series);
			System.out.println(seriesSelection.getXValue()+" *** "+seriesSelection.getValue());
			
		}
	}
	
	private XYMultipleSeriesDataset getDateSet(){
		initDateData();
		XYMultipleSeriesDataset dateSet = null;
		dateSet = new XYMultipleSeriesDataset();
		XYSeries series = new XYSeries("测试一线");
		for (int i = 0; i < dates1.size(); i++) {
			series.add(0+i, dates1.get(i).getScore());
		}
		
		dateSet.addSeries(series);
		
		series = new XYSeries("测试二线");
		for (int i = 0; i < dates2.size(); i++) {
			series.add(0+i, dates2.get(i).getScore());
		}
		dateSet.addSeries(series);
		
		return dateSet;
	}
	
	private void initDateData(){
		 dates1 = new ArrayList<DateScore>();
		 DateScore score;
		 for (int i = 0; i < 10; i++) {
			 score = new DateScore();
			 score.setDate("2013-12-1"+i);
			 score.setScore(7000-(i%2)*1000);
			 dates1.add(score);
		}
		 
		 dates2 = new ArrayList<DateScore>();
		 for (int i = 0; i < 10; i++) {
			 score = new DateScore();
			 score.setDate("2013-12-1"+i);
			 score.setScore(7000-(i%3)*1000);
			 dates2.add(score);
		}
	 }

	private XYMultipleSeriesRenderer getRenderer(){
		XYMultipleSeriesRenderer renderer = null;
		renderer = new XYMultipleSeriesRenderer();
		
		XYSeriesRenderer xyRenderer = new XYSeriesRenderer();
		xyRenderer.setColor(Color.GREEN);
		xyRenderer.setPointStyle(PointStyle.CIRCLE);
		xyRenderer.setLineWidth(3f);
		renderer.addSeriesRenderer(xyRenderer);
		xyRenderer = new XYSeriesRenderer();
		xyRenderer.setColor(Color.BLUE);
		xyRenderer.setPointStyle(PointStyle.POINT);
		xyRenderer.setLineWidth(3f);
		renderer.addSeriesRenderer(xyRenderer);
		
		renderer.setMargins(new int[] { 0, 0, 60, 0 });
		
		setChartSettings(renderer, "", "", "", 0, 9, 0, 7100, 0x999, 0x999);
		setRenderer(renderer);
		return renderer;
	}

	protected void setChartSettings(XYMultipleSeriesRenderer renderer,
			String title, String xTitle, String yTitle, double xMin,
			double xMax, double yMin, double yMax, int axesColor,
			int labelsColor) {
		renderer.setChartTitle(title);
		renderer.setXTitle(xTitle);
		renderer.setYTitle(yTitle);
		renderer.setXAxisMin(xMin);
		renderer.setXAxisMax(xMax);
		renderer.setYAxisMin(yMin);
		renderer.setYAxisMax(yMax);
	}
	
	private void setRenderer(XYMultipleSeriesRenderer renderer){
		renderer.setShowGrid(true);
		renderer.setXLabelsAlign(Align.CENTER);
		renderer.setYLabelsAlign(Align.RIGHT);
		renderer.setPointSize(6f);

		renderer.setZoomEnabled(false, false);
		renderer.setPanLimits(new double[] { 0, 10 + 0.5, 0, 100 });
		renderer.setPanEnabled(true, false);
		renderer.setShowLegend(false);

		renderer.setMarginsColor(Color.WHITE);
		renderer.setGridColor(0xFF999999);
		renderer.setAxesColor(0xFF999999);

		
		renderer.setShowLabels(true);
		renderer.setXLabelsColor(0xFF666666);
		renderer.setLabelsTextSize(26f);
		renderer.setYLabels(10);
		renderer.setXLabels(9);

		for (int i = 0; i < dates1.size(); i++) {
			String s = dates1.get(i).getDate();
//			if (xValue == null || xValue.size() < 1)
//				s = lh.getxLabelTextList().get(i);
//			else
//				s = xValue.get(i).toString();
			renderer.addXTextLabel(i, s);
		}

		 for (int i = 0; i < dates1.size(); i++) {
			int y =  dates1.get(i).getScore()/1000 * 1000;
			renderer.addYTextLabel(y, y+"");
		 }
		 renderer.setYTitle("aaaaaaaaaaaaaaaaa");

		renderer.setClickEnabled(true);
		renderer.setSelectableBuffer(30);
		renderer.setShowGridY(false);
	}
	
}
