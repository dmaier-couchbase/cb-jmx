package com.couchbase.jmx.mbeans;

/**
 * Defines the cmd_get MBean
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public interface CmdGetMBean {
    
    public String getSamples();
    public double getMin();
    public double getMax();
    public double getAvg();
    public double getMedian();
    public double getNext();
}
