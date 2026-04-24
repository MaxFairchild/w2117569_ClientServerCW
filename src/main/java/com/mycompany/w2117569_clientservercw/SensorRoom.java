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

import java.util.List;
import java.util.ArrayList;


@Path("/rooms")
public class SensorRoom {
    
    
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response getAllRooms(){
           return Response.ok(roomDatabase).build();
        }
       
       
        @GET
        @Path("/{id}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response getRoomByID(@PathParam("id") String id) {
        // 1. Loop through your static database
            for (Room room : roomDatabase) {
                
                if (room.getRoomID().equalsIgnoreCase(id)) {
                
                    return Response.ok(room).build();
                }
            }

            // 4. If the loop finishes without finding a match, return 404
        return Response.status(Response.Status.NOT_FOUND)
                   .entity("{\"error\": \"Room with ID " + id + " not found\"}")
                   .build();
        }
        
        //private UriInfo uriInfo; 
        
        public static List<Room> roomDatabase = new ArrayList<>(); 
        
        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        public Response createRoom(Room room){
           System.out.println("Signal Recieved");
           try {
               if (room == null) {
               return Response.status(Response.Status.BAD_REQUEST).build();
               }
           
                System.out.println("Creating Room: "+ room.getRoomName());
                roomDatabase.add(room); // Save the room to the list of rooms
                System.out.println("Room stored. Total rooms: " + roomDatabase.size());
                
                return Response.status(Response.Status.CREATED).entity(roomDatabase).build();
                
           } catch (Exception e) {
               System.out.println("beebeeboop");
           }
           return null;
       }
        
    
           
        @POST
        @Path("/{roomID}/addSensor/{sensorID}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response linkSensorToRoom(@PathParam("roomID") String rID, @PathParam("sensorID") String sID) {
        // 1. Find the Room
        Room targetRoom = null;
        for (Room r : roomDatabase) {
            if (r.getRoomID().equalsIgnoreCase(rID)) {
                targetRoom = r;
                break;
            }
        }

        // 2. Room Validation
        if (targetRoom == null) {
            return Response.status(404).entity("{\"error\":\"Room not found\"}").build();
        }
        
        // 3. Sensor Validation
        boolean SensorExists = false;
        //list of Sensors from Database
        for (Sensor s : SensorHandler.sensorDatabase) {
            if (s.getSensorID().equalsIgnoreCase(sID)) {
                SensorExists = true;
                break;
            }
        }
        
        // 3.2. Does sensor EXIST?
        if (!SensorExists){
            return Response.status(Response.Status.NOT_FOUND) //
                       .entity("{\"error\":\"Sensor " + sID + " does not exist. Create the sensor first meow.\"}")
                       .build();
        }
        
        // 4. DUPLICATE CHECK: Is this sensor already in THIS room?
        if (targetRoom.getSensorIDs().contains(sID)) {
        return Response.status(Response.Status.CONFLICT)
                       .entity("{\"error\":\"Sensor " + sID + " is already assigned to this room\"}")
                       .build();
    }

        // 5. Capacity Check
        if (targetRoom.getSensorIDs().size() >= targetRoom.getCapacity()) {
            return Response.status(409).entity("{\"error\":\"Room is at full capacity\"}").build();
        }

        // 6. Add the ID to the list After all checks
        targetRoom.getSensorIDs().add(sID);
    
            return Response.ok(targetRoom).build();
        }   
 
        @POST
        @Path("/{id}")
        @Consumes(MediaType.APPLICATION_JSON)
        public Response updateRoom(@PathParam("id") int id, String roomData) {
            // Logic to update room sensor thresholds or metadata
            return Response.noContent().build(); // 204 No Content
       
       
       
        }
       
       
        @DELETE
        @Path("/{roomID}/Delete")
        @Produces(MediaType.APPLICATION_JSON)
        public Response deleteRoom(@PathParam("roomID") String roomID) {
        try {
        // 1. Attempt to find the room
            Room roomToDelete = null;
            for (Room r : roomDatabase) {
                if (r.getRoomID().equalsIgnoreCase(roomID)) {
                    roomToDelete = r;
                    break;
                }
            }

        // 2. Handle missing room first
        if (roomToDelete == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("{\"error\": \"Room does not exist\"}")
                           .build();
        }

        // 3. The "Conflict" Check
        // If sensors exist, we manually throw an exception to jump to the catch block
        if (roomToDelete.getSensorIDs() != null && !roomToDelete.getSensorIDs().isEmpty()) {
            throw new IllegalStateException("Sensors Present");
        }

        // 4. Execution
        roomDatabase.remove(roomToDelete);
        return Response.ok("{\"message\": \"Room " + roomID + " deleted successfully\"}").build();

        } catch (IllegalStateException e) {
        // This catches our manual "Sensors Present" check
        return Response.status(Response.Status.CONFLICT)
                       .entity("{\"message\": \"Cannot delete room with Sensors, please change or delete sensors first\"}")
                       .build();
        } catch (Exception e) {
        // This catches any other unexpected crashes (like NullPointer)
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity("{\"error\": \"An unexpected error occurred: " + e.getMessage() + "\"}")
                            .build();
    }
}
        
   //
        
}




