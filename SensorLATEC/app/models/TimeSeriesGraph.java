package models;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class TimeSeriesGraph {
	
	TimeSeriesCollection dataset; 
	
	public TimeSeriesGraph(){
		dataset = new TimeSeriesCollection();
	}
	
	public void createLine(String nome, List<Long> time, List<Integer> values){
		TimeSeries temp = new TimeSeries(nome);
		
		for(int i = 0; i < time.size(); i++){
			temp.add( new Day(new Date(time.get(i))), values.get(i));
		}
		
		dataset.addSeries(temp);
	}
	
	public void saveChart(String name, String x, String y){
		JFreeChart chart = ChartFactory.createTimeSeriesChart(
				name,
				x, 
				y,
				dataset,
				true,
				true,
				false);

		try {
			ChartUtilities.saveChartAsJPEG(new File("chart.jpg"), chart, 500, 300);
		} catch (IOException e) {
			System.err.println("Problem occurred creating chart.");
		}	
	}

}
