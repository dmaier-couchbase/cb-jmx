package com.couchbase.jmx.mbeans;

import com.couchbase.jmx.helper.SampleStats;
import com.couchbase.jmx.httpclient.RESTClientFactory;
import com.couchbase.jmx.job.LocalCache;
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
     * The interval when the clock should be reseted
     */
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
     * The url to access for the statistics
     */
    protected String url;
     
    /**
     * A clock counter
     */
    protected int curr;
         
    /**
     * The default constructor
     * @param name
     */
    public BaseSampled(String name) {
    
        this.name = name;
        this.curr = 0;
        
        
        try {
        
            this.url = "/pools/default/buckets/" + RESTClientFactory.getClient().getBucket() + "/stats";
        
        } catch (Exception ex) {
            
            LOG.log(Level.SEVERE, "Could not access the target bucket name: {0}", ex.getMessage());
        }
    }
    
    
    /**
     * To retrieve the samples
     *
     * @return
     */
    protected Double[] retSamples() {
        
        List<Double> result = new ArrayList<>();

        JSONObject json = LocalCache.get(url);

        JSONObject op = (JSONObject) json.get("op");
        JSONObject sampl = (JSONObject) op.get("samples");
        JSONArray cmd = (JSONArray) sampl.get(this.getName());

        for (Object c : cmd) {

            double val = Double.parseDouble(c.toString());

            result.add(val);
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
      
      retSamples();
      return SampleStats.min(this.samples);
        
    }
    
    /**
     * Get the maximum sample
     * 
     * @return 
     */
    protected double retMax() {

        retSamples();
        return SampleStats.max(this.samples);
    }

    

    /**
     * Get the average value from the samples
     * 
     * @return 
     */
    protected double retAvg() {
             
        retSamples();
        return SampleStats.avg(this.samples);
        
    }

    /**
     * Get the median value from the samples, the median is the value in the middle
     * of the samples
     * 
     * @return 
     */
    protected double retMedian() {
        
        retSamples();
        return SampleStats.median(this.samples);
    }
    
    
    /**
     * Loops over the samples
     * 
     * @return 
     */
    protected double retNext() {
        
        retSamples();
        
        if (curr >= INTERVAL) {

            curr = 0;
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
