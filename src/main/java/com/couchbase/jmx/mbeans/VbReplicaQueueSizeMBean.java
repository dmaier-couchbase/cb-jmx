package com.couchbase.jmx.mbeans;

/**
 * Defines the vb_replica_queue_size MBean
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public interface VbReplicaQueueSizeMBean {
    
    public String getSamples();
    public double getMin();
    public double getMax();
    public double getAvg();
    public double getMedian();
    public double getNext();
}
