/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.w2117569_clientservercw;

/**
 *
 * @author maxar
 */
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/sensors")
public class SensorHandler {
    
    
    public static List<Sensor> sensorDatabase = new ArrayList<>();
    
    @Path("/{sensorId}/reading")
    public SensorReadingHandler getReadingResource(@PathParam("sensorId") String sID) {
    // We pass the sensorId to the next class so it knows which sensor it's dealing with
        return new SensorReadingHandler(sID);
    }   
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSensor(Sensor s) {
        try {
        // 1. Find the Room and keep a reference to it
            Room targetRoom = null;
            for (Room r : SensorRoom.roomDatabase) {
            if (r.getRoomID().equalsIgnoreCase(s.getRoomID())) {
                targetRoom = r;
                break;
            }
        }

   
        if (targetRoom == null) {
            throw new IllegalArgumentException("Room ID " + s.getRoomID() + " does not exist.");
        }


        targetRoom.getSensorIDs().add(s.getSensorID());


        sensorDatabase.add(s);

        } catch (IllegalArgumentException e) {
        // Return a 400 Bad Request or 404 Not Found
            return Response.status(409)
                           .entity("{\"error\": \"" + e.getMessage() + "\"}")
                           .build();
        } catch (Exception e) {
            return Response.status(500)
                           .entity("{\"error\": \"Internal server error: " + e.getMessage() + "\"}")
                           .build();
    }
    
    
    return null;
}

    
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSensors() {
    try {
            if (sensorDatabase == null) {
                return Response.ok(new ArrayList<Sensor>()).build();
            }
        // If the list exists, return it
            return Response.ok(sensorDatabase).build();
        } catch (Exception e) {
        // This will catch the crash and give you a hint in Postman instead of a 500
            return Response.serverError()
                       .entity("{\"error\":\"" + e.getMessage() + "\"}")
                       .build();
        }
    
    
    }
    
    
    
}
