/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import model.AnimalNoDB;

/**
 * REST Web Service
 *
 * @author Frederik Dahl <cph-fd76@cphbusiness.dk>
 */
@Path("animals")
public class AnimalDemo {

    @Context
    private UriInfo context;
    private static Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Creates a new instance of AnimalDemo
     */
    public AnimalDemo() {
    }

    /**
     * Retrieves representation of an instance of rest.AnimalDemo
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getJson() {
       return "Vuf... (message from a dog)"; 
    }
    
    @Path("animal_list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson2() {
        return "{\"Dog\"\"Cat\"\"Mouse\"\"Bird\"}"; 
    }
    
    @Path("animal")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson3() {
        AnimalNoDB animal = new AnimalNoDB("Duck", "Quack!"); 
        String jsasonString = GSON.toJson(animal); 
        return jsasonString; 
         
    }
    

    /**
     * PUT method for updating or creating an instance of AnimalDemo
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
