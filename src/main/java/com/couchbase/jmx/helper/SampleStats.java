package com.couchbase.jmx.helper;

import java.util.Arrays;
import java.util.Collections;

/**
 * A helper class to get statistical metrics from samples
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public class SampleStats {
    
    /**
     * The maximum value of the set of samples
     * 
     * @param samples
     * @return 
     */
    public static double max(Double[] samples)
    {
        return Collections.max(Arrays.asList(samples));
    }
   
    /**
     * The minimum value of the set of samples
     * 
     * @param samples
     * @return 
     */
    public static double min(Double[] samples)
    {
        return Collections.min(Arrays.asList(samples));
    }
    
    /**
     * The average value of the set of samples.
     * 
     * Sum of all values divided by the count of samples.
     * 
     * @param samples
     * @return 
     */
    public static double avg(Double[] samples)
    { 
        double sum = 0;
        
        for (Double curr : samples) {
         
            sum = sum + curr;
            
        }
        
        return (sum / samples.length);
    }
     
    /**
     * The median of the samples.
     * 
     * Unlike the average, the median is the value which is in the middle of the set
     * of samples.
     * 
     * @param samples
     * @return 
     */
    public static double median(Double[] samples)
    {
        int pos = (samples.length / 2) - 1;
        
        return samples[pos];
    }
    
}
