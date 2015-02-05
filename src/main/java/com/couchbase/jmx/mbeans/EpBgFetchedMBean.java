package com.couchbase.jmx.mbeans;

/**
 * Defines the ep_bg_fetched MBean
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public interface EpBgFetchedMBean {
    
    public String getSamples();
    public double getMin();
    public double getMax();
    public double getAvg();
    public double getMedian();
    public double getNext();
}
