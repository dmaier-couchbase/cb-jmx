package com.couchbase.jmx.job;

import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;

/**
 * A local cache which stores retrieved JSON objects by their URL
 * 
 * The idea is that a Job fills the local cache wherby the managed beans then only access the
 * cached results. 
 * 
 * This makes sense because the Couchbase stats are provided as a series of samples. Which means that you get all the 
 * values of the last minute (whereby you could also use a lower accurancy of hours, weeks and so on.
 * 
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public class LocalCache {
    
    /**
     * The central cache instance
     */
    public static Map<String, JSONObject> cache = new HashMap<>();
    
    
    /**
     * To store a value inside the local the cache
     * @param relUrl
     * @param value 
     */
    public static void store(String relUrl, JSONObject value)
    {
        cache.put(relUrl, value);
    }
    
    /**
     * To get a value from the local cache
     * @param relUrl
     * @return 
     */
    public static JSONObject get(String relUrl)
    {
        return cache.get(relUrl);
    }
    
}
