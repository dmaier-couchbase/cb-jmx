/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.couchbase.jmx.mbeans;

import com.couchbase.jmx.httpclient.RESTClientFactory;
import com.couchbase.jmx.job.JobRunner;
import com.couchbase.jmx.job.StatsUpdateJob;
import org.junit.Test;

/**
 *
 * @author David Maier <david.maier at couchbase.com>
 */
public class GetCmdTest {
    
    @Test
    public void testGetCmd() throws InterruptedException {
    
        RESTClientFactory.createClient("192.168.7.160", 8091, "couchbase", "couchbase", "test");
    
        JobRunner.run(new StatsUpdateJob());
        
        Thread.sleep(1000);
        
        CmdGet cmd = new CmdGet();
        
        String samples = cmd.getSamples();
        
        System.out.println(samples);
    
    }
}
