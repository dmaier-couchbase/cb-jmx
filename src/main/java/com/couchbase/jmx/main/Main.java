package com.couchbase.jmx.main;

import com.couchbase.jmx.agents.CBAgent;
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
        
        //Validate params
        if (validateArgs(args))
        {
            LOG.info("Starting CBAgent ...");
            CBAgent agent = new CBAgent();
            waitForEnterPressed();    
        }
        else
        {
            usage();
        }    
    }
    
    /**
     * Validate the arguments
     * 
     * @param args 
     */
    private static boolean validateArgs(String[] args)
    {
        if (args == null) return false;
        if (args.length == 0) return false;
        
        try
        {
            Long.parseLong(args[1]);
        }
        catch (NumberFormatException ex)
        {
            return false;
        }
        
        //By default return true;
        return true;
    }

    /**
     * Print usage information
     */
    private static void usage()
    {
        System.out.println("Use: java -jar cb-jmx.jar ${host} ${port} ${user} ${password}\n" +
                           "Please make sure that you executed 'rmiregistry 9999 &' before!");
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
