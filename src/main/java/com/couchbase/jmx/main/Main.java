package com.couchbase.jmx.main;

import agents.CBAgent;
import java.io.IOException;
import java.util.logging.Logger;

/**
 *
 * @author David Maier <david.maier at couchbase.com>
 */
public class Main {
    
    private static final Logger LOG = Logger.getLogger(Main.class.getName());
    
    /**
     * Run the service
     * 
     * The RMI registry needs to be started first: rmiregistry 9999 & 
     * The property -Dcom.sun.management.jmxremote should be used
     * 
     * 
     * @param args 
     */
    public static void main(String[] args) {
        
        LOG.info("Starting CBAgent ...");
        CBAgent agent = new CBAgent();
        waitForEnterPressed();    
    }
    
    
    /**
     * Wait until a key is pressed
     */
    private static void waitForEnterPressed() {

        try {
            System.out.println("Press <ENTER> to continue");
            System.in.read();

        } catch (IOException e) {

            LOG.severe(e.getMessage());
        }
    }
}
