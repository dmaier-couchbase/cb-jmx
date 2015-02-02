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
java -cp cb-jmx-1.0-SNAPSHOT.jar com.couchbe.jmx.main.Main 192.168.7.130 8091 couchbase couchbase test
```

* Connect via JConsole to it
```
jconsole service:jmx:rmi:///jndi/rmi://localhost:9999/server
``

