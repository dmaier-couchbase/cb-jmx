package com.couchbase.jmx.main;

import com.couchbase.jmx.agents.CBAgent;
import com.couchbase.jmx.httpclient.RESTClientFactory;
import com.couchbase.jmx.job.JobRunner;
import com.couchbase.jmx.job.StatsUpdateJob;
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
     * You then can connect via:
     *    jconsole service:jmx:rmi:///jndi/rmi://localhost:9999/server
     * 
     * 
     * @param args 
     */
    public static void main(String[] args) {
        
        //Validate params
        if (validateArgs(args))
        {
            //Preparing the connection to Couchbase
            LOG.info("Connecting to Couchbase REST service ...");
            
            String host = args[0];
            int port = Integer.parseInt(args[1]);
            String user = args[2];
            String password = args[3];
            String bucket = args[4];
            
            RESTClientFactory.createClient(host, port, user, password, bucket);
       
            //Start the JMX agent
            LOG.info("Starting CBAgent ...");   
            CBAgent agent = new CBAgent();
            
            //Only execute the job if the agent was started
            if (agent.isRunning())
            {
            
                LOG.info("Starting statistics update job");
                JobRunner.run(new StatsUpdateJob());
            
            }
            
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
        if (args.length != 5) return false;
        
        try
        {
            Integer.parseInt(args[1]);
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
        System.out.println("Use: java -jar cb-jmx.jar ${host} ${port} ${user} ${password} ${bucket}\n" +
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
