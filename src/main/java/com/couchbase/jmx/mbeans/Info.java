package com.couchbase.jmx.mbeans;

/**
 * Implements the Info MBean
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public class Info implements InfoMBean {

            
    /**
     * The info message
     */
    private String info = null;

    /**
     * The default constructor
     */
    public Info() {
        
        this.info = "JMX Service for Couchbase";
    }
    
    
    /**
     * To get the info message
     * @return 
     */
    @Override
    public String getInfo() {
        
        return this.info;
    }
}
