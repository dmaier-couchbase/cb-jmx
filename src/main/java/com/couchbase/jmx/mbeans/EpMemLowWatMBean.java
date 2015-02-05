package com.couchbase.jmx.mbeans;

/**
 * Defines the ep_mem_low_wat MBean
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public interface EpMemLowWatMBean {
    
    public String getSamples();
    public double getMin();
    public double getMax();
    public double getAvg();
    public double getMedian();
    public double getNext();
}
