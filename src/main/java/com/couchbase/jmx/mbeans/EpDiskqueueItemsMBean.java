package com.couchbase.jmx.mbeans;

/**
 * Defines the ep_diskqueue_items MBean
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public interface EpDiskqueueItemsMBean {
    
    public String getSamples();
    public double getMin();
    public double getMax();
    public double getAvg();
    public double getMedian();
    public double getNext();
}
