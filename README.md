# A JMX monitoring service for Couchbase

The idea of the service is to expose the Couchbase statistics via JMX. So tools like E.G. 'jconsole' can connect in order to access the metrics.

The current state is quite experimental.

To start it:

* Run the RMI registry
```
rmiregistry 9999 &
```

* Execute the JAR file
```
java -jar cb-jmx-1.0-SNAPSHOT-jar-with-dependencies.jar 192.168.7.160 8091 couchbase couchbase test
```

* Connect via JConsole to it
```
jconsole service:jmx:rmi:///jndi/rmi://localhost:9999/server
```


# Currently available Managed Beans
The MBeans section of JConsole now shows you a 'Couchbase' subsection. So far the following Managed Beans are implemented:

* info: Shows general information about the JMX service
* cmd_get: Shows the information how many get commands are currently processed for the given bucket. This is the number of commands those are invoked by the application.
* cmd_set: Shows the information how many set commands are currently processed for the given bucket. This is the number of commands those are invokded by the application.
* cpu_utilization_rate: This is the current CPU load for all cluster nodes.
* delete_hits: This is the number of delete operations those did hit a document in Couchbase. So it is the number of successfully performed delete operations those were invoked by the application.
* ep_bg_fetched: This is the number of fetches from disk. The cache hit ratio for get operations is (ep_bg_fetched / cmd_get) * 100 .
* ep_diskqueue_items: This is the current size of the disk write queue. The disk write queue size should not increase lineary.
* ep_mem_high_wat: This is the high watermark in megabytes which is configured. When the consumed memory (for the bucket) reaches the high watermark, then the system will begin to eject items until the low water mark threashold is reached again.
* ep_mem_low_wat: This is the low watermark in megabytes which is configured. If this threashold is reached, then you can expect that that items will be ejected from memory soon. If you want a working set of 100% then you should increase the memory quota of your bucket as soon as the low water mark is reached.
* mem_used: The current memory usage for the given bucket.
* vb_replica_queue_size: The size of the replication queue. As the disk write queue replication should keep up, which means that you don't want to see a linear increasement of this value.

# How to add new bucket metrics

The Managed Bean naming convention says that you should have an Interface which is named 'MyMetricMBean' and an implementation class 'MyMetric'. The interface looks like the following one:

```
package com.couchbase.jmx.mbeans;

/**
 * Defines the cmd_get MBean
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public interface CmdGetMBean {
    
    public String getSamples();
    public double getMin();
    public double getMax();
    public double getAvg();
    public double getMedian();
    public double getNext();
}
```

If you want to access one of the samples metrics of a bucket then there is already a base class which is called 'BaseSampled'. In this case your own class has only to call the super constructor with the name of the metric. The base class implements some 'ret*' methods in order to retrieve the metric values. To access the values via your MBean you just can wrap this retrieval methods with the getters those are defined by your MBean interface:

```
package com.couchbase.jmx.mbeans;
import java.util.Arrays;


/**
 * Implements the cmd_get MBean
 * 
 * The metric cmd_get is the number of high level set operations per second.
 * 
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public class CmdGet extends BaseSampled implements CmdGetMBean {
         
    /**
     * The default constructor
     */
    public CmdGet() {

        super("cmd_get");
    }
   
    
    @Override
    public String getSamples() {
        
       return Arrays.toString(retSamples());
    }
    
    @Override
    public double getMin() {
        
        return retMin();
    }
    
    @Override
    public double getMax() {
        
        return retMax();
    }

    @Override
    public double getAvg() {
             
        return retAvg();
        
    }

    @Override
    public double getMedian() {
        
        return retMedian();
    }   

    @Override
    public double getNext() {
        
        return retNext();
    }
}

```

# Screenshots
![alt tag](https://raw.github.com/dmaier-couchbase/cb-jmx/master/assets/screen2.png)

