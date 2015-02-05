package com.couchbase.jmx.mbeans;
import java.util.Arrays;


/**
 * Implements the ep_diskqueue_items MBean
 * 
 * The metric ep_diskqueue_items is the current size of the disk write queue.
 * 
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public class EpDiskqueueItems extends BaseSampled implements EpDiskqueueItemsMBean {
         
    /**
     * The default constructor
     */
    public EpDiskqueueItems() {

        super("ep_diskqueue_items");
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
