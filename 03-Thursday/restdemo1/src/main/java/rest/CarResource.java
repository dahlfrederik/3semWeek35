package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CarDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Frederik Dahl <cph-fd76@cphbusiness.dk>
 */
@Path("car")
public class CarResource {

    @Context
    private UriInfo context;
    private static Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static List<CarDTO> cars = new ArrayList(); 
    

    public CarResource() {
        if(cars.isEmpty()){
            cars.add(new CarDTO("BMW",100, 2015)); 
            cars.add(new CarDTO("AUDI", 50000, 2013)); 
            cars.add(new CarDTO("BMW", 1521312, 2017)); 
        }
    }

    //Her vises at der returneres et JSON element fra GET-Request. 
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        return "[]"; 
    }
    
    @Path("driver")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson2() {
        return "{\"name\": \"Kurt Wonnegut\"}"; 
    }
    
    @Path("carobject")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJSON3() {
        CarDTO car = new CarDTO("BMW",100, 2015); 
        String jsasonString = GSON.toJson(car); 
        return jsasonString; 
        
    }
    
    @Path("allcars")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJSON4() {
        String jsasonString = GSON.toJson(cars); 
        return jsasonString; 
        
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
