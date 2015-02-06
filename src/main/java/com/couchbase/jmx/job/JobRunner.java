package com.couchbase.jmx.job;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Just a helper class to create jobs
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public class JobRunner {
    
    /**
     * The thread executor
     */
     private static final ExecutorService service = Executors.newSingleThreadExecutor();
    
    /**
     * Run the stats update nob
     * @param job
     */
    public static void run(Runnable job)
    {
        service.execute(job);
    }
            
}
