package com.couchbase.jmx.httpclient;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 * The REST Client to communicate with the Couchbase statistical services
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public class RESTClient {
    
    private static final Logger LOG = Logger.getLogger(RESTClient.class.getName());
    
    /**
     * Couchbase host
     */
    private final String host;
    
    /**
     * Couchbase port
     */
    private final int port;
    
    /**
     * Couchbase user
     */
    private final String user;
    
    /**
     * Couchbase password
     */
    private final String password;
    
    /**
     * Couchbase bucket
     */
    private final String bucket;
    
    
    /**
     * The inner HTTP client
     */
    private HttpClient httpClient;
    
    /**
     * The HTTP context
     */
    private HttpClientContext httpCtx;
    
    
    /**
     * The HTTP host
     */
    private HttpHost httpHost;
    

    /**
     * The full constructor
     * 
     * @param host
     * @param port
     * @param user
     * @param password
     * @param bucket 
     */
    public RESTClient(String host, int port, String user, String password, String bucket) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
        this.bucket = bucket;
        
    }
    
    public void connect()
    { 
        this.httpClient = HttpClients.createDefault();
     
        AuthScope httpScope = new AuthScope(this.host, this.port);
        UsernamePasswordCredentials httpCred = new UsernamePasswordCredentials(this.user, this.password);
        CredentialsProvider provider = new BasicCredentialsProvider();
        provider.setCredentials(httpScope, httpCred);
        
        AuthCache authCache = new BasicAuthCache();
        BasicScheme basicAuth = new BasicScheme();
        this.httpHost = new HttpHost(this.host, this.port, "http");
        authCache.put(httpHost, basicAuth);
        
        this.httpCtx = HttpClientContext.create();
        this.httpCtx.setCredentialsProvider(provider);
        this.httpCtx.setAuthCache(authCache);
    }
    
    public JSONObject get(String relUrl) throws IOException
    {
        LOG.log(Level.INFO, "Requested: {0}", relUrl);
        
        HttpGet getReq = new HttpGet(relUrl);
        
        HttpResponse resp = this.httpClient.execute(this.httpHost, getReq, this.httpCtx);
        
        String respStr = EntityUtils.toString(resp.getEntity());
        
        return (JSONObject) JSONValue.parse(respStr);
    }

    
    /**
     * To get the the bucket which should be inspected
     * @return 
     */
    public String getBucket() {
        return bucket;
    }
    
    /**
     * To get the host where the admin REST service runs
     * @return 
     */
    public String getHost() {
        return host;
    }

    /**
     * To get the port of the admin REST service
     * @return 
     */
    public int getPort() {
        return port;
    }

    /**
     * To get the user to connect with
     * @return 
     */
    public String getUser() {
        return user;
    }
 
    /**
     * To get the password of the connecting user
     * @return 
     */
    public String getPassword() {
        return password;
    }
       
    
}
