package com.couchbase.jmx.job;

import com.couchbase.jmx.httpclient.RESTClient;
import com.couchbase.jmx.httpclient.RESTClientFactory;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;

/**
 *
 * @author David Maier <david.maier at couchbase.com>
 */
public class StatsUpdateJob implements Runnable {

    /**
     * Logger 
     */
    private static final Logger LOG = Logger.getLogger(StatsUpdateJob.class.getName());
    
    /**
     * The default error message
     */
    private static final String ERR = "Could not retrieve the statistics from Couchbase!";
    
    /**
     * The update intervall in ms
     */
    public static final int INTERVAL = 5000;
   
    
    /**
     * The REST client
     */
    protected static RESTClient client; 

    public StatsUpdateJob() {
    
        try {
            
            client = RESTClientFactory.getClient();
       
        } catch (Exception ex) {
        
             LOG.log(Level.SEVERE,ERR, ex.getMessage());
        }
    
    }  
    
    @Override
    public void run() {
    
        while (true) {

            //Refresh the loclal cache
            try {

                String url = "/pools/default/buckets/" + client.getBucket() + "/stats";

                JSONObject json = client.get(url);

                LocalCache.store(url, json);

            } catch (IOException ex) {

                LOG.log(Level.SEVERE, ERR, ex.getMessage());

            }

            //Wait
            try {

                Thread.sleep(INTERVAL);

            } catch (InterruptedException ex) {

                LOG.log(Level.SEVERE, "Wait failed: {0}", ex.getMessage());
            }
        }    
    }
    
}
