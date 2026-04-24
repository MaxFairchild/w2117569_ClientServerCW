/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.w2117569_clientservercw;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author maxar
 */
@ApplicationPath("app/v1")
public class ApplicationConfig extends ResourceConfig {
    public ApplicationConfig() {
        register(cw1.class);
        register(SensorRoom.class);
    }
    
}
