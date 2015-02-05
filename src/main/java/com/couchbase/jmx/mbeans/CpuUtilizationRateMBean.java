package com.couchbase.jmx.mbeans;

/**
 * Defines the cpu_utilization_rate MBean
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public interface CpuUtilizationRateMBean {
    
    public String getSamples();
    public double getMin();
    public double getMax();
    public double getAvg();
    public double getMedian();
    public double getNext();
}
