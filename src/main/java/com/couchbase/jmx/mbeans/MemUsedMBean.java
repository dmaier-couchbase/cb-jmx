package com.couchbase.jmx.mbeans;

/**
 * Defines the mem_used MBean
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public interface MemUsedMBean {
    
    public String getSamples();
    public double getMin();
    public double getMax();
    public double getAvg();
    public double getMedian();
    public double getNext();
}
