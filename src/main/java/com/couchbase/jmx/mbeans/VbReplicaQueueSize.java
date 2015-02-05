package com.couchbase.jmx.mbeans;
import java.util.Arrays;


/**
 * Implements the vb_replica_queue_size MBean
 * 
 * The metric vb_replica_queue_size is the size of the replication queue.
 * 
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public class VbReplicaQueueSize extends BaseSampled implements VbReplicaQueueSizeMBean {
         
    /**
     * The default constructor
     */
    public VbReplicaQueueSize() {

        super("vb_replica_queue_size");

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
