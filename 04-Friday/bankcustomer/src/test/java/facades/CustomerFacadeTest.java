package facades;

import entities.BankCustomer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerFacadeTest {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static final CustomerFacade FE = CustomerFacade.getFacadeExample(emf);

    public CustomerFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
//      EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        //String firstName, String lastName, String accountNumber, double balance, int customerRanking, String internalInfo
        try {
            em.getTransaction().begin();
            em.persist(new BankCustomer("Thor", "Christensen", "f123fa", 20000.0, 2, "betaler ikke til tiden"));
            em.persist(new BankCustomer("Josef", "Marc", "g123ga", 10000.0, 3, "betaler ikke til tiden"));
            em.persist(new BankCustomer("Frederik", "Dahl", "fbd102", 50000.0, 1, "betaler til tiden"));
            em.persist(new BankCustomer("Frederik", "Jørgensen", "fqu123", 500.0, 4, "skylder penge"));
            em.getTransaction().commit();

        } finally {
            em.close();

        }
    }

    @AfterAll
    public static void tearDown() {
        emf.close();
    }

    @Test
    public void testGetCustomerByID() {
        CustomerFacade cf = CustomerFacade.getFacadeExample(emf);
        int id = 1;
        int expResult = 1;
        int result = cf.getCustomerByID(id).getCustomerID();

        assertEquals(expResult, result);
    }

    @Test
    public void testGetCustomersByName() {
        CustomerFacade cf = CustomerFacade.getFacadeExample(emf);
        String name = "Frederik";

        int expSize = 2;
        int resultSize = cf.getCustomerByName(name).size();

        assertEquals(expSize, resultSize);
    }

    @Test
    public void testgetAllEmployees() {
        CustomerFacade cf = CustomerFacade.getFacadeExample(emf);
        int expResult = 4;
        int result = cf.getAllBankCustomers().size();
        assertEquals(expResult, result);
    }

    @Test
    public void testAddCustomer() {
        CustomerFacade cf = CustomerFacade.getFacadeExample(emf);
        BankCustomer test = new BankCustomer("Thora", "Christensen", "f123fa", 20000.0, 2, "skylder 200k");

        int expResult = 4;
        int result = cf.getAllBankCustomers().size();
        assertEquals(expResult, result);
        int id = 4;
        int expId = id;
        int resultID = cf.getCustomerByID(id).getCustomerID();
        assertEquals(expId, resultID);

    }
}
