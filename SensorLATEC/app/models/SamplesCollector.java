package models;

import java.util.HashMap;
import java.util.Map;




import com.sun.spot.io.j2me.radiogram.*;
import com.sun.spot.peripheral.ota.OTACommandServer;

import javax.microedition.io.*;

import models.Spot;
import Exceptions.*;

/**
 *    
 * @author Felipe Sales
 * 
 */
public class SamplesCollector {
    // Broadcast port on which we listen for sensor samples
    private static final int HOST_PORT = 67;
    
    private static final int SLEEP_TIME = 10000;
    
    TimeSeriesGraph chart;
    public Map<Long,Spot> spots;
        
    public SamplesCollector(){  	
    	spots = new HashMap<Long,Spot>();
    	chart = new TimeSeriesGraph();	
    }
  
    private Spot[] spotsToArray() throws SpotsNotFoundException{
    	Spot[] arraySpots = new Spot[spots.size()];
	    
    	if(spots.size() != 0){
	    	int i = 0;
	    	for(Map.Entry<Long,Spot> entry : spots.entrySet() ){
	    		arraySpots[i] = entry.getValue();
	    	}
    	}else{
    		throw new SpotsNotFoundException();
    	}
    	
    	return arraySpots;
    }
    
    public void createChart(){
    	Spot[] temp;
		try {
			temp = spotsToArray();
		
	    	for(int i = 0; i < temp.length; i++){
	    		chart.createLine(String.valueOf(temp[i].getAddr()), temp[i].getTime(), temp[i].getVal());
	    	}
		} catch (SpotsNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    private Spot findSpot(long addr) {
    	
    	if(!spots.containsKey(addr)){
    		spots.put(addr, new Spot(addr) );
    	}
    	
    	return spots.get(addr);	    	
    }    
    	
    private void addData(long time, int value, long addr) {
        findSpot(addr).addData(time, value);
    }
    
    public void run() throws Exception {

    	//OTACommandServer.start("SendDataDemo-GUI");	    
    	
        RadiogramConnection rCon;
        Radiogram dg;
        
        try {
            // Open up a server-side broadcast radiogram connection
            // to listen for sensor readings being sent by different SPOTs
            rCon = (RadiogramConnection) Connector.open("radiogram://:" + HOST_PORT);
            dg = (Radiogram)rCon.newDatagram(rCon.getMaximumLength());
        } catch (Exception e) {
             System.err.println("setUp caught " + e.getMessage());
             throw e;
        }
        
        while (true) {
            try {
                // Read sensor sample received over the radio
                rCon.receive(dg);
        
                long time = dg.readLong();      // read time of the reading
                int val = dg.readInt();         // read the sensor value
                
                addData(time, val, dg.getAddressAsLong());
                System.out.println(val);
                
            } catch (Exception e) {
                System.err.println("Caught " + e +  " while reading sensor samples.");
                throw e;
            }
        }
    }
    
    
    
    public int getSleepTime(){
    	return SLEEP_TIME;
    }
    
  
    public static void main(String[] args) throws Exception {
        // register the application's name with the OTA Command server & start OTA running
        OTACommandServer.start("SendDataDemo-GUI");
        
        SamplesCollector app = new SamplesCollector();
        
        app.run();
    }
}

