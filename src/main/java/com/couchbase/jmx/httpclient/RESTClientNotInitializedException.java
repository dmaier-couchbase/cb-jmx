package com.couchbase.jmx.httpclient;

/**
 *
 * @author David Maier <david.maier at couchbase.com>
 */
public class RESTClientNotInitializedException extends Exception {

    @Override
    public String toString() {
        return "The REST client was not yet initialized!";
    }
    
    
}
