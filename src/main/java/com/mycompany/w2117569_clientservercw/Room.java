/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.w2117569_clientservercw;



import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author maxar
 */
public class Room {
    
    private String roomID;
    private String roomName;
    private int capacity;
    private List<String> sensorIDs = new ArrayList<>();

    public Room() {}

    
    public String getRoomID() {
        return roomID; 
    }
    
    public void setRoomID(String roomID) {
        this.roomID = roomID; 
    }

    public String getRoomName() {
        return roomName; 
    }
    
    public void setRoomName(String roomName) {
        this.roomName = roomName; 
    }

    public int getCapacity() {
        return capacity; 
    }
    
    
    public void setCapacity(int capacity) {
        this.capacity = capacity; 
    }

    public List<String> getSensorIDs() {
        return sensorIDs; 
    }
    
    public void setSensorIDs(List<String> sensorIDs) {
        this.sensorIDs = sensorIDs; 
    }
    
    
    
}