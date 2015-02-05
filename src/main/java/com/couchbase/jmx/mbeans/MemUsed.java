package com.couchbase.jmx.mbeans;
import java.util.Arrays;


/**
 * Implements the mem_used MBean
 * 
 * The metric mem_used is the currently used memory for the given bucket.
 * 
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public class MemUsed extends BaseSampled implements MemUsedMBean {
         
    /**
     * The default constructor
     */
    public MemUsed() {

        super("mem_used");

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
