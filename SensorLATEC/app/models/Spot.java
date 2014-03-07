package models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Felipe Sales
 */
public class Spot {
	
	private List<Long> time = new ArrayList<Long>();
    private List<Integer> val = new ArrayList<Integer>();
    
    private long addr;
    private boolean visible = true;

    public Spot(long addr) {
       this.addr = addr;
    }
    
    public void alternaVisible(){
        if(visible){
            visible = false;
        }else{
            visible = true;
        }
    }

    public boolean isVisible(){
    	return visible;
    }

    public void addData(long t, int v) {
        time.add(t);
        val.add(v);   
    }
       
    public List<Long> getTime() {
		return time;
	}

    public List<Integer> getVal() {
		return val;
	}

    public void setAddr(long addr) {
		this.addr = addr;
	}

	public long getAddr(){
        return addr;
    }
}