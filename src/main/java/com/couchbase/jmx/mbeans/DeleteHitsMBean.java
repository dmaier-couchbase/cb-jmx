package com.couchbase.jmx.mbeans;

/**
 * Defines the delete_hits MBean
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public interface DeleteHitsMBean {
    
    public String getSamples();
    public double getMin();
    public double getMax();
    public double getAvg();
    public double getMedian();
    public double getNext();
}