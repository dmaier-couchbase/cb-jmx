package com.couchbase.jmx.mbeans;
import java.util.Arrays;


/**
 * Implements the cmd_get MBean
 * 
 * The metric cmd_set is the number of high level set operations per second.
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public class CmdSet extends BaseSampled implements CmdSetMBean {
         
    /**
     * The default constructor
     */
    public CmdSet() {

        super("cmd_set");
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
