/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbfacade;

import Entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Frederik Dahl <cph-fd76@cphbusiness.dk>
 */
public class CustomerFacadeTest {

    private static EntityManagerFactory emf;
    private static EntityManager em; 
    
    @BeforeAll
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("pu");
        em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Customer c1 = new Customer("Thor", "Christensen");
            Customer c2 = new Customer("Josef", "Marc");
            Customer c3 = new Customer("Frederik", "Dahl");
            em.persist(c1);
            em.persist(c2);
            em.persist(c3);
            em.getTransaction().commit();

        } finally {
            em.close();
            emf.close();
        }
    }
    
    @AfterAll
    public static void afterClass(){
        
    }

    /**
     * Test of getCustomerFacade method, of class CustomerFacade.
     */
    @Test
    public void testGetCustomerFacade() {
        System.out.println("getCustomerFacade");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CustomerFacade result = CustomerFacade.getCustomerFacade(emf);
        assertNotNull(result);
    }

    /**
     * Test of findByID method, of class CustomerFacade.
     */
    @Test
    public void testFindByID() {
        System.out.println("findByID");
        CustomerFacade cf = CustomerFacade.getCustomerFacade(emf);
        int id = 1;
        int expResult = 1;
        int result = cf.findByID(id).getId();

        assertEquals(expResult, result);
    }

    /**
     * Test of findByLastName method, of class CustomerFacade.
     
    @Test
    public void testFindByLastName() {
        String lName = "Christensen";
        CustomerFacade cf = CustomerFacade.getCustomerFacade(emf);
        int id = 1;
        Customer c1 = cf.findByID(id);
        System.out.println("Bruger fra findByID " + c1);
        Customer expResult = c1;
        Customer result = cf.findByLastName(lName);
        System.out.println("Bruger fra findByLastName: " + result);
        assertEquals(expResult, result);
    }
    * */

    /**
     * Test of getNumberOfCustomers method, of class CustomerFacade.
     *
     */
    @Test
    public void testGetNumberOfCustomers() {
        CustomerFacade cf = CustomerFacade.getCustomerFacade(emf);
        int expResult = 3;
        int result = cf.getNumberOfCustomers();
        assertEquals(expResult, result);
    }

    /**
     * Test of allCustomer method, of class CustomerFacade.
     */
    @Test
    public void testAllCustomer() {
        System.out.println("allCustomer");
        CustomerFacade cf = CustomerFacade.getCustomerFacade(emf);
        int expResult = 3;
        int result = cf.allCustomer().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of addCustomer method, of class CustomerFacade.
     */
    @Test
    public void testAddCustomer() {
        System.out.println("addCustomer");
        String fName = "Testi";
        String lName = "Testison";
        CustomerFacade cf = CustomerFacade.getCustomerFacade(emf);
        cf.addCustomer(fName, lName); 
        int expResult = 4;
        int result = cf.allCustomer().size();
        assertEquals(expResult, result);
        int id = 4; 
        int expId = id; 
        int resultID = cf.findByID(id).getId();
        assertEquals(expId, resultID);
        
        cf.deleteCustomer(id);
    }
    
    @Test
    public void testDeleteCustomer(){
        System.out.println("deleteCustomer");
        CustomerFacade cf = CustomerFacade.getCustomerFacade(emf);
        int expResult = 2; 
        cf.deleteCustomer(3);
        int result = cf.allCustomer().size();
        assertEquals(expResult, result); 
        
        Customer c3 = cf.findByID(3); 
        assertNull(c3); 
    }

}
