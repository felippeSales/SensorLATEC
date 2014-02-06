/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sunspotworld.demo;

import java.awt.Color;

/**
 *
 * @author Felipe Sales
 */
public class Spot {

    private static final int MAX_SAMPLES = 10000;
    private int index = 0;
    private long[] time = new long[MAX_SAMPLES];
    private int[] val = new int[MAX_SAMPLES];
    private long addr;
    private Color color;

    public Spot(Color color, long addr) {
       this.color = color;
       this.addr = addr;

    }

    public void addData(long t, int v) {
        time[index] = t;
        val[index++] = v;
    }

    public long[] getTime(){
        return time;
    }

    public int[] getVal(){
        return val;
    }
    public int getIndex(){
       return index;
    }
    
    public Color getColor(){
        return color;
    }

    public long getAddr(){
        return addr;
    }


}