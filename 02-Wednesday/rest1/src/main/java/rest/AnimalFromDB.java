package rest;

import com.google.gson.Gson;
import entity.Animal;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Frederik Dahl <cph-fd76@cphbusiness.dk>
 */
@Path("animals_db")
public class AnimalFromDB {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AnimalFromDB
     */
    public AnimalFromDB() {
    }

    @Path("animals")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimals() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
            List<Animal> animals = query.getResultList();
            return new Gson().toJson(animals);
        } finally {
            em.close();
        }
    }

    @Path("animalbyid/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalByID(@PathParam("id") int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Animal an = em.find(Animal.class, id);

            return new Gson().toJson(an);

        } finally {
            em.close();
        }
    }

    @Path("animalbytype/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalByType(@PathParam("type") String type) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a WHERE a.type = :type", Animal.class);
            query.setParameter("type", type); 
            List<Animal> animals = query.getResultList();
            return new Gson().toJson(animals);
        } finally {
            em.close();
        }
    }

    @Path("random_animal")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String randomAnimal() {
        EntityManager em = emf.createEntityManager();
        try {
            //GETS AMOUNTS OF ANIMALS IN DB
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
            List<Animal> animals = query.getResultList();
            int amountOfAnimals = animals.size();
            //AMOUNT + 1 SINCE ITS INDEX 0 BASED IN AN ARRAY AND WE'RE USING THE ID
            int randomId = new Random().nextInt(amountOfAnimals+1);
            if (randomId != 0) {
                Animal an = em.find(Animal.class, randomId);
                return new Gson().toJson(an);
            } else {
                return randomAnimal(); 
            }
        
        } finally {
            em.close();
        }
    }

    /**
     * PUT method for updating or creating an instance of AnimalFromDB
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

}
