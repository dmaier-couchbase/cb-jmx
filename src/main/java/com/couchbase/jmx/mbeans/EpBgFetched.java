package com.couchbase.jmx.mbeans;
import java.util.Arrays;


/**
 * Implements the ep_bg_fetched MBean
 * 
 * The metric ep_bg_fetched is the number of fetches from disk.
 * The cache hit ratio is (ep_bg_fetched / cmd_gets * 100).
 *
 * @author David Maier <david.maier at couchbase.com>
 */
public class EpBgFetched extends BaseSampled implements EpBgFetchedMBean {
         
    /**
     * The default constructor
     */
    public EpBgFetched() {

        super("ep_bg_fetched");
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
