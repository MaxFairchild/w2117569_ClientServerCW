/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.w2117569_clientservercw;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
/**
 *
 * @author maxar
 */
@Path("/hello")
public class cw1 {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getHello(){
        return "API IS ACTIVE";
    }
    
    
    
}
