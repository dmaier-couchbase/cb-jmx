/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.couchbase.jmx.httpclient;

import org.junit.Test;


/**
 *
 * @author David Maier <david.maier at couchbase.com>
 */
public class RESTClientTest {
    

   
    @Test
    public void testConnect() throws Exception {
    
        RESTClientFactory.createClient("192.168.7.160", 8091, "couchbase", "couchbase", "test");
        
        System.out.println(RESTClientFactory.getClient().get("/pools/default"));
        
    }
}
