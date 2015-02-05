package com.couchbase.jmx.mbeans;

import com.couchbase.jmx.helper.SampleStats;
import com.couchbase.jmx.httpclient.RESTClient;
import com.couchbase.jmx.httpclient.RESTClientFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * A base class which provides access to the samples
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public class BaseSampled {
    
    /**
     * Logger 
     */
    private static final Logger LOG = Logger.getLogger(BaseSampled.class.getName());
    
    /**
     * Default error message
     */
    private static final String ERR = "Could not access the samples!";
    private static final long INTERVAL = 59;
    
    /**
     * The name of the series of samples
     */
    protected String name;
    
    /**
     * The samples
     */
    protected Double[] samples;
    
    /**
     * A clock
     */
    protected int curr;
    
    
    /**
     * The REST client
     */
    protected static RESTClient client; 
    
     
    /**
     * The default constructor
     */
    public BaseSampled(String name) {
    
        this.name = name;
        this.curr = 0;
        
        try {
            
            client = RESTClientFactory.getClient();
       
        } catch (Exception ex) {
        
             LOG.log(Level.SEVERE,ERR, ex.getMessage());
        }
    }
    
    
    /**
     * To retrieve the samples
     *
     * @return
     */
    protected Double[] retSamples() {
        List<Double> result = new ArrayList<>();

        try {

            JSONObject json = client.get("/pools/default/buckets/" + client.getBucket() + "/stats");

            JSONObject op = (JSONObject) json.get("op");
            JSONObject sampl = (JSONObject) op.get("samples");
            JSONArray cmd = (JSONArray) sampl.get(this.getName());

            for (Object c : cmd) {

                double val = Double.parseDouble(c.toString());

                result.add(val);
            }

        } catch (IOException ex) {

            LOG.log(Level.SEVERE, ERR, ex.getMessage());

        }

        this.samples = result.toArray(new Double[result.size()]);

        return this.samples;

    }
    
    /**
     * Get the minimum sample
     * 
     * @return 
     */
    protected double retMin() {
        
      return SampleStats.min(this.samples);
        
    }
    
    /**
     * Get the maximum sample
     * 
     * @return 
     */
    protected double retMax() {

        return SampleStats.max(this.samples);
    }

    

    /**
     * Get the average value from the samples
     * 
     * @return 
     */
    protected double retAvg() {
             
        return SampleStats.avg(this.samples);
        
    }

    /**
     * Get the median value from the samples
     * @return 
     */
    protected double retMedian() {
        
        return SampleStats.median(this.samples);
    }
    
    protected double retNext() {
        
        if (curr >= INTERVAL) {

            curr = 0;
            
            retSamples();

        }
        else
        {
             curr++;
        }

        return this.samples[curr];
    }

    /**
     * Get the name of the series
     * 
     * @return 
     */
    public String getName() {
        return name;
    }
    
    
    
}
