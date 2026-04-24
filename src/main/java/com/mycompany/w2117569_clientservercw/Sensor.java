/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.w2117569_clientservercw;

/**
 *
 * @author maxar
 */
public class Sensor {
    private String sensorID;
    private String type;
    private String status;
    private double currentValue;
    private String roomID;
    
    public Sensor(){
        
    }
    
    public Sensor(String sID, String t){
        this.sensorID = sID;
        this.type = t;
    }
    
    public Sensor(String sID, String t, String s){
        this.sensorID = sID;
        this.type = t;
        this.status = s;
    }
    
    public String getSensorID(){
        return this.sensorID;
    }
    
    public void setSensorID(String id){
        this.sensorID = id;
    }
    
    public String getType(){
        return this.type;
    }
    
    public void setType(String TTT){
        this.type = TTT;
    }
    
    public String getStatus(){
        return this.status;
    }
    
    public void setStatus(String s) {
        if (s == null) {
            this.status = "UNKNOWN";
            return;
        }
    
        
        if (s.equalsIgnoreCase(this.status)) {
            return;
        }

        switch (s.toLowerCase()) {
            case "active":
                this.status = "ACTIVE";
                break;
            case "maintenance":
                this.status = "MAINTENANCE";
                break;
            case "offline":
                this.status = "OFFLINE";
                break;
            default:
                System.out.println("Invalid Sensor State: " + s);
                this.status = "UNKNOWN";
                break;
    }
}
    
    public double getCurrentVal(){
        return this.currentValue;
        
    }
    
    public void setCurrentValue(double cV){
        this.currentValue = cV;
    }
    
    public String getRoomID(){
        return this.roomID;
    }
    
    public void setRoomID(String ID){
        this.roomID = ID;
    }
}
