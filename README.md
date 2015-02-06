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

The MBeans section of JConsole now shows you a 'Couchbase' subsection. So far only 2 Beans are implemented
* Info: Shows general information about the JMX service
* GetCMD: Show the information how many get commands are currently processed (min, max, avg, samples)

# How to add new bucket metrics

TODO

# Screenshots
![alt tag](https://raw.github.com/dmaier-couchbase/cb-jmx/master/assets/screen.png)

