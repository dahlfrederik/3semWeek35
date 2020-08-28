/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Employee;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Frederik Dahl <cph-fd76@cphbusiness.dk>
 */
public class EmployeeFacadeTest {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    @BeforeAll
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("pu");
        em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(new Employee("Josef Marc", "Glostrupvej", 25000));
            em.persist(new Employee("Frederik Dahl", "Tagensvej", 1000000));
            em.persist(new Employee("Thor Christensen", "Frederiksbergvej", 26000));
            em.getTransaction().commit();

        } finally {
            em.close();

        }
    }

    
     /**
     * Test of getCustomerFacade method, of class CustomerFacade.
     * Checks for connection 
     */
    @Test
    public void testGetCustomerFacade() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EmployeeFacade result = EmployeeFacade.getEmployeeFacade(emf);
        assertNotNull(result);
    }
    
    /**
     * Checks that the expected ID and the id that comes from the method call is the same
     */
    @Test
    public void testGetEmployeeByID() {
        EmployeeFacade cf = EmployeeFacade.getEmployeeFacade(emf);
        int id = 1;
        int expResult = 1;
        int result = cf.getEmployeeByID(id).getId();
        
        assertEquals(expResult, result);
    }
    
     /**
     * Checks that the expected ID and the id that comes from the method call is the same
     */
    @Test
    public void testGetEmployeeByName() {
        EmployeeFacade cf = EmployeeFacade.getEmployeeFacade(emf);
        String name = "Josef Marc";
        String expResult = "Josef Marc";
        String result = cf.getEmployeeByName(name).getName(); 

        assertEquals(expResult, result);
    }
    
    @Test
    public void testgetAllEmployees() {
        EmployeeFacade cf = EmployeeFacade.getEmployeeFacade(emf);
        int expResult = 3;
        int result = cf.getAllEmployees().size();
        assertEquals(expResult, result);
    }

    @Test
    public void testAddCustomer() {
        System.out.println("addCustomer");
        String name = "Testi Testison";
        String adress = "Testvej";
        int salary = 1000; 
        EmployeeFacade cf = EmployeeFacade.getEmployeeFacade(emf);
        cf.createEmployee(name, adress, salary); 
        int expResult = 4;
        int result = cf.getNumberOfEmployees(); 
        assertEquals(expResult, result);
        int id = 4; 
        int expId = id; 
        int resultID = cf.getEmployeeByID(id).getId();
        assertEquals(expId, resultID);
        
        cf.deleteEmployee(id);
    }
    
    @Test
    public void testHighestSalary(){
        EmployeeFacade cf = EmployeeFacade.getEmployeeFacade(emf);
        Employee expEmployee = cf.getEmployeeByName("Frederik Dahl"); 
        Employee resultEmployee = cf.getEmployeesWithHighestSalary();
        
        //HVORFOR KAN MAN IKKE SAMMENLIGNE ENTITY OBJEKTER
        //assertEquals(expEmployee, resultEmployee); 
        
        String expName = "Frederik Dahl"; 
        String resultName = resultEmployee.getName(); 
        assertEquals(expName, resultName); 
    }
    
    
}
