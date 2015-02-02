/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.couchbase.jmx.mbeans;

import com.couchbase.jmx.httpclient.RESTClientFactory;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author David Maier <david.maier at couchbase.com>
 */
public class GetCmdTest {
    
    @Test
    public void testGetCmd() {
    
        RESTClientFactory.createClient("192.168.7.160", 8091, "couchbase", "couchbase", "test");
        
        GetCmd cmd = new GetCmd();
        
        Double[] samples = cmd.getSamples();
        
        for (Double val : samples) {
            
            System.out.println(val);
        }
    
    }
}
