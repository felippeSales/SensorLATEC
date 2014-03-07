package models;

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
    private boolean visible = true;

    public Spot(Color color, long addr) {
       this.color = color;
       this.addr = addr;
    }
    
    public void alternaVisible(){
        if(visible == true){
            visible = false;
        }else{
            visible = true;
        }
    }

    public boolean isVisible(){
        if(visible){
            return true;
        }else{
            return false;
        }
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