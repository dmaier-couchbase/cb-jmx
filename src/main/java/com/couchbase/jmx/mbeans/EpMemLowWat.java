package com.couchbase.jmx.mbeans;
import java.util.Arrays;


/**
 * Implements the ep_mem_low_wat MBean
 * 
 * The metric ep_mem_low_wat is the low water mark in megabytes.
 *  
 * The system will start to eject items when the  ep_mem_high_wat is reached by
 * Least Reacently Used. 
 * 
 * Evictions of active and replica data occur with the ratio probability of
 * 40% (active data) to 60% (replica data) until the memory usage reaches the 
 * low watermark
 * 
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public class EpMemLowWat extends BaseSampled implements EpMemLowWatMBean {
         
    /**
     * The default constructor
     */
    public EpMemLowWat() {

        super("ep_mem_low_wat");
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
