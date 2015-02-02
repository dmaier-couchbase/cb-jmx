package com.couchbase.jmx.mbeans;

/**
 * Describes the get_cmd MBean
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public interface GetCmdMBean {
    
    public Double[] getSamples();
    public double getMax();
    public double getMin();
    public double getAvg();
}
