package rest;

import com.google.gson.Gson;
import dto.CustomerDTO;
import entities.BankCustomer;
import facades.CustomerFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("bankcustomer")
public class BankCustomerResource {
    
    //NOTE: Change Persistence unit name according to your setup
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu"); 
    CustomerFacade facade =  CustomerFacade.getFacadeExample(emf);

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("byid/{id}")
    public String employeeById(@PathParam("id") int id) {
        try{
            CustomerFacade cf = CustomerFacade.getFacadeExample(emf); 
            CustomerDTO cus = cf.getCustomerByID(id);           
            return new Gson().toJson(cus);
        } catch (javax.persistence.NoResultException e) {
            String errorString = "The id " + id + " is not in the database and therefore this program cannot show you the result";
            return new Gson().toJson(errorString);
        }
    }
        
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("all")
    public String allEmployees() {
        try{
            CustomerFacade cf = CustomerFacade.getFacadeExample(emf); 
            List<BankCustomer> customerList = cf.getAllBankCustomers(); 
            return new Gson().toJson(customerList);
        }catch (javax.persistence.NoResultException e) {
            String errorString = "The function is either not working or there might not be any data in the database. IT HAS BEEN CONTACTED";
            return new Gson().toJson(errorString);
        }
       
    }
}
    

