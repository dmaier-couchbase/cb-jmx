package com.couchbase.jmx.httpclient;

/**
 *
 * @author David Maier <david.maier at couchbase.com>
 */
public class RESTClientFactory {
 
    
   /**
    * The instance of the REST client
    */ 
   private static RESTClient instance;

    
    /**
     * To get the REST client
     * 
     * @return
     * @throws Exception 
     */
    public static RESTClient getClient() throws Exception {
        
        if (instance != null)
                return instance;
        else
            throw new RESTClientNotInitializedException(); 
    }
    
    
    /**
     * Create a REST client
     * 
     * @param host
     * @param port
     * @param user
     * @param password
     * @param bucket
     * @return 
     */
    public static RESTClient createClient(String host, int port, String user, String password, String bucket)
    {
        instance = new RESTClient(host, port, user, password, bucket);
        instance.connect();
        return instance;
    }
    
    
}
