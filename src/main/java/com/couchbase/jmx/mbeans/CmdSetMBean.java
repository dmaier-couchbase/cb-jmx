package com.couchbase.jmx.mbeans;

/**
 * Defines the set_cmd MBean
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public interface CmdSetMBean {
    
    public String getSamples();
    public double getMin();
    public double getMax();
    public double getAvg();
    public double getMedian();
    public double getNext();
}
