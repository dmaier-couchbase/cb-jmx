package com.couchbase.jmx.mbeans;
import java.util.Arrays;


/**
 * Implements the delete_hits MBean
 * 
 * The metric delte_hits is the number of high level Delete operations per 
 * second those did hit an existing document.
 * 
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public class DeleteHits extends BaseSampled implements DeleteHitsMBean {
         
    /**
     * The default constructor
     */
    public DeleteHits() {

        super("delete_hits");
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
