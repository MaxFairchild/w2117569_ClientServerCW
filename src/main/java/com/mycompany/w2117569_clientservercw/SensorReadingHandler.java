/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.w2117569_clientservercw;


/**
 *
 * @author maxar
 */
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class SensorReadingHandler {
    
    private String sensorId;
    
    private static List<SensorReading> readingsDatabase = new ArrayList<>();

    // Constructor that receives the ID from the Locator
    public SensorReadingHandler(String sID) {
        this.sensorId = sID;
    }

   @POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response addReading(SensorReading reading) {
    try {
        Sensor currentSensor = null;
        for (Sensor s : SensorHandler.sensorDatabase) {
            if (s.getSensorID().equalsIgnoreCase(this.sensorId)) {
                currentSensor = s;
                break;
            }
        }

        // 
        if (currentSensor != null && currentSensor.getStatus().equalsIgnoreCase("maintenance")) {
            // Throwing an exception to trigger the forbidden eror catch block
            throw new IllegalAccessException("Sensor is under maintenance");
        }

        reading.setID(this.sensorId);
        reading.setTimestamp(System.currentTimeMillis());
        readingsDatabase.add(reading);

        return Response.status(201).entity(reading).build();

        //Question 5 part 3
        } catch (IllegalAccessException e) {
            return Response.status(Response.Status.FORBIDDEN)
                           .entity("{\"error\": \"Action Forbidden: Cannot add readings to a sensor in maintenance mode.\"}")
                           .build();
        } catch (Exception e) {
            return Response.serverError()
                            .entity("{\"error\": \"Internal error: " + e.getMessage() + "\"}")
                            .build();
    }
}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SensorReading> getReadingsForSensor() {
        List<SensorReading> filtered = new ArrayList<>();
        for (SensorReading r : readingsDatabase) {
            if (r.getID().equalsIgnoreCase(sensorId)) {
                filtered.add(r);
            }
        }
        return filtered;
    }
}
