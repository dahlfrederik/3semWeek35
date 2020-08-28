package dbfacade;

import Entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Frederik Dahl <cph-fd76@cphbusiness.dk>
 */
public class CustomerFacade {

    private static EntityManagerFactory emf;
    private static CustomerFacade instance;

    private CustomerFacade() {
    }

    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    public Customer findByID(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Customer cus = em.find(Customer.class, id);
            return cus;
        } finally {
            em.close();
        }
    }

    /**
     * public Customer findByLastName(String lName) { EntityManager em =
     * emf.createEntityManager(); try { List<Customer> customerList =
     * em.createQuery("SELECT e FROM Customer e WHERE
     * e.lastName").getResultList(); Customer cus = em.find(Customer.class,
     * lName); return cus; } finally { em.close(); } }
     */
    
    
    public int getNumberOfCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            List<Customer> customerList = em.createQuery("SELECT e FROM Customer e").getResultList();
            return customerList.size();
        } finally {
            em.close();
        }
    }

    public List<Customer> allCustomer() {
        EntityManager em = emf.createEntityManager();
        try {
            List<Customer> customerList = em.createQuery("SELECT e FROM Customer e").getResultList();
            return customerList;
        } finally {
            em.close();
        }

    }

    public Customer addCustomer(String fName, String lName) {
        Customer cus = new Customer(fName, lName);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cus);
            em.getTransaction().commit();
            return cus;
        } finally {
            em.close();
        }
    }

    public void deleteCustomer(int id) {
        EntityManager em = emf.createEntityManager();
        try { 
            em.getTransaction().begin();
            em.remove(em.find(Customer.class, id));
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

}
