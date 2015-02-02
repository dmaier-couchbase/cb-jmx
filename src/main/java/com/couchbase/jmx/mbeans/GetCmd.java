package com.couchbase.jmx.mbeans;

import com.couchbase.jmx.httpclient.RESTClient;
import com.couchbase.jmx.httpclient.RESTClientFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Implements the get_cmd MBean
 * 
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public class GetCmd implements GetCmdMBean {
       
    private static final Logger LOG = Logger.getLogger(GetCmd.class.getName());
    private static RESTClient client; 
    
    
    public static final String NAME = "get_cmd";
    public static final String ERR = "Could not access " + NAME + " statistics: {0}";
    
    /**
     * The samples retrieved from Couchbase
     */
    private Double[] samples;
    
    /**
     * The default constructor
     */
    public GetCmd() {
        
        try {
            
            client = RESTClientFactory.getClient();
       
        } catch (Exception ex) {
        
             LOG.log(Level.SEVERE,ERR, ex.getMessage());
        }
        
    }
   
   /**
    * To get the samples via a REST call
    * 
    * @return 
    */
    @Override
    public Double[] getSamples() {
     
        List<Double> result = new ArrayList<>();
        
        try {
            
            JSONObject json = client.get("/pools/default/buckets/" + client.getBucket() + "/stats");
        
            JSONObject op = (JSONObject) json.get("op");
            JSONObject sampl = (JSONObject) op.get("samples");
            JSONArray cmd_get = (JSONArray) sampl.get("cmd_get");
            
            for (Object curr : cmd_get) {
                
                double val = Double.parseDouble(curr.toString());
                
                result.add(val);
            }
        
        } catch (IOException ex) {
            
            LOG.log(Level.SEVERE, ERR, ex.getMessage());
        
        }
        
        this.samples = result.toArray(new Double[result.size()]);
        
        return this.samples;
    }

    /**
     * Get the maximum sample
     * 
     * @return 
     */
    @Override
    public double getMax() {
        
        getSamples();
        return Collections.max(Arrays.asList(this.samples));
    }

    /**
     * Get the minimum sample
     * 
     * @return 
     */
    @Override
    public double getMin() {
        
      getSamples();
      return Collections.min(Arrays.asList(this.samples));
        
    }

    /**
     * Get the average value from the samples
     * 
     * @return 
     */
    @Override
    public double getAvg() {
             
        getSamples();
        
        double sum = 0;
        
        for (Double curr : samples) {
         
            sum = sum + curr;
            
        }
        
        return (sum / this.samples.length);
        
    }
    
    
}
