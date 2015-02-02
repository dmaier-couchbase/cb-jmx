package com.couchbase.jmx.agents;

import com.couchbase.jmx.mbeans.Info;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

/**
 * Acts as a container for MBeans
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public class CBAgent {
    
    //Logger
    private static final Logger LOG = Logger.getLogger(CBAgent.class.getName());
    private static final String NAME = "CBAgent";
    private static final String URL = "service:jmx:rmi:///jndi/rmi://localhost:9999/server";
    
    //The MBean server
    private MBeanServer mbs = null;
     
     
    /**
     * The constructor of the agent
     */ 
    public CBAgent() {
    
           
        mbs = ManagementFactory.getPlatformMBeanServer();

        try {

            //Register the beans
            LOG.info("Registering MBeans ...");
            ObjectName infoBeanName = createUniqueName("info");
            mbs.registerMBean(new Info(), infoBeanName);
            
            //Start the RMI connector
            LOG.info("Starting RMI connector ...");
            JMXServiceURL url = new JMXServiceURL(URL);          
            JMXConnectorServer cs = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mbs);          
            cs.start();
                       
        } catch (IOException | InstanceAlreadyExistsException | MBeanRegistrationException | MalformedObjectNameException | NotCompliantMBeanException ex) {
           
            LOG.log(Level.SEVERE, "Could not bind beans: {0}", ex.getMessage());
        }
        
    }
    
    /**
     * Creates an unique name of an MBean by using the short name
     * 
     * @param shortName
     * @return
     * @throws MalformedObjectNameException 
     */
    private ObjectName createUniqueName(String shortName) throws MalformedObjectNameException
    {
        String name = NAME + ":name=" + shortName;
        
        return new ObjectName(name);
        
    }
    
    
}
