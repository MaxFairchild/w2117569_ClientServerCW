/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.w2117569_clientservercw;

/**
 *
 * @author maxar
 */
public class SensorReading {
    private String id;
        
    private long timestamp;
        
    private double value;
        
    public SensorReading(){
            
    }
        
    
        
        
    public void setID(String ID){
        this.id = ID;
    }
        
    public String getID(){
        return this.id;
    }
        
    public void setTimestamp(long ts){ 
        timestamp = ts;
    }
        
    public long getTimeStamp(){
        return this.timestamp;
    }
    
}
