package com.couchbase.jmx.mbeans;
import java.util.Arrays;


/**
 * Implements the cpu_utilization_rate MBean
 * 
 * The metric cpu_utilization_rate is the current percentage of the CPU load 
 * over all cluster nodes.
 * 
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public class CpuUtilizationRate extends BaseSampled implements CpuUtilizationRateMBean {
         
    /**
     * The default constructor
     */
    public CpuUtilizationRate() {

        super("cpu_utilization_rate");
    }
   
    
    @Override
    public String getSamples() {
        
       return Arrays.toString(retSamples());
    }
    
    @Override
    public double getMin() {
        
        return retMin();
    }
    
    @Override
    public double getMax() {
        
        return retMax();
    }

    @Override
    public double getAvg() {
             
        return retAvg();
        
    }

    @Override
    public double getMedian() {
        
        return retMedian();
    }   

    @Override
    public double getNext() {
        
        return retNext();
    }
}
