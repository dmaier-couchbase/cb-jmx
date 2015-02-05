package com.couchbase.jmx.mbeans;
import java.util.Arrays;


/**
 * Implements the ep_mem_high_wat MBean
 * 
 * The metric ep_high_low_wat is the high water mark in megabytes. 
 * 
 * When the high water mark threshold is reached Couchbase will start to eject 
 * items from the RAM until the low water mark is reached again.
 * 
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public class EpMemHighWat extends BaseSampled implements EpMemHighWatMBean {
         
    /**
     * The default constructor
     */
    public EpMemHighWat() {

        super("ep_mem_high_wat");
    }
   
    
    @Override
    public String getSamples() {
        
       return Arrays.toString(retSamples());
    }
    
    @Override
    public double getMin() {
        
        return retMin();
    }
    
    @Override
    public double getMax() {
        
        return retMax();
    }

    @Override
    public double getAvg() {
             
        return retAvg();
        
    }

    @Override
    public double getMedian() {
        
        return retMedian();
    }   

    @Override
    public double getNext() {
       
        return retNext();
    }
}
