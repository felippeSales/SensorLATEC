package controllers;

import models.SamplesCollector;

public class Tester {
	
	public static void main(String[] args) throws Exception {

		SamplesCollector c = new SamplesCollector();
		
		c.run();
		
		c.createChart();
		
	}

}
