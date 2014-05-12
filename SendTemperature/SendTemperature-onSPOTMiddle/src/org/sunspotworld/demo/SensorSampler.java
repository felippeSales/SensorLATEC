package org.sunspotworld.demo;

import com.sun.spot.io.j2me.radiogram.*;
import com.sun.spot.resources.Resources;
import com.sun.spot.resources.transducers.ITriColorLED;
import com.sun.spot.resources.transducers.ITemperatureInput;
import com.sun.spot.util.Utils;

import javax.microedition.io.*;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class SensorSampler extends MIDlet {

    private static final int HOST_PORT = 67;
    //private static final int SAMPLE_PERIOD = 10 * 1000;  // in milliseconds
    
    protected void startApp() throws MIDletStateChangeException {
        RadiogramConnection rCon = null;
        Datagram dg = null;
        String ourAddress = System.getProperty("IEEE_ADDRESS");
        ITemperatureInput temperatureSensor = (ITemperatureInput)Resources.lookup(ITemperatureInput.class);
        ITriColorLED led = (ITriColorLED)Resources.lookup(ITriColorLED.class, "LED7");
        
        System.out.println("Starting sensor sampler application on " + ourAddress + " ...");

	// Listen for downloads/commands over USB connection
	new com.sun.spot.service.BootloaderListenerService().getInstance().start();

        try {
            // Open up a broadcast connection to the host port
            // where the 'on Desktop' portion of this demo is listening
            rCon = (RadiogramConnection) Connector.open("radiogram://broadcast:" + HOST_PORT);
            dg = rCon.newDatagram(rCon.getMaximumLength());
        } catch (Exception e) {
            System.err.println("Caught " + e + " in connection initialization.");
            notifyDestroyed();
        }
        
        while (true) {
            try {
                
                rCon.receive(dg);

                long time = dg.readLong();      // read time of the reading
                double val = dg.readDouble();         // read the sensor value
                
                // Flash an LED to indicate a sampling event
                led.setRGB(255, 255, 255);
                led.setOn();
                Utils.sleep(50);
                led.setOff();

                // Package the time and sensor reading into a radio datagram and send it.
                dg.reset();
                dg.writeLong(time);
                dg.writeDouble(val);
                rCon.send(dg);

//                System.out.println("Temperature in Celsius = " + (Math.floor(reading*10)/10));
                
                // Go to sleep to conserve battery
  //              Utils.sleep(SAMPLE_PERIOD - (System.currentTimeMillis() - now));
            } catch (Exception e) {
                System.err.println("Caught " + e + " while collecting/sending sensor sample.");
            }
        }
    }
    
    protected void pauseApp() {
        // This will never be called by the Squawk VM
    }
    
    protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
        // Only called if startApp throws any exception other than MIDletStateChangeException
    }
}
