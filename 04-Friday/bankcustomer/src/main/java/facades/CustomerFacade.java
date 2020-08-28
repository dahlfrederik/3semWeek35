package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class CustomerFacade {

    private static CustomerFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private CustomerFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CustomerFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CustomerDTO getCustomerByID(int id) {
       EntityManager em = emf.createEntityManager();
        try {
            Query query2 = em.createQuery("Select e FROM BankCustomer e WHERE e.id = :id");
            query2.setParameter("id", id);
            BankCustomer cus = (BankCustomer) query2.getSingleResult();
            CustomerDTO  cusDto= new CustomerDTO(cus); 
            return cusDto;
        } finally {
            em.close();
        }
    }

    public List<CustomerDTO> getCustomerByName(String name) {
        EntityManager em = emf.createEntityManager(); 
        try{
            Query query2 = em.createQuery("Select e FROM BankCustomer e WHERE e.firstName = :name");
            query2.setParameter("name", name);
         
            List<BankCustomer> customerList = query2.getResultList(); 
            List<CustomerDTO> customerDTOList = new ArrayList(); 
             for (BankCustomer bankCustomer : customerList) {
                CustomerDTO cusDTO = new CustomerDTO(bankCustomer); 
                customerDTOList.add(cusDTO); 
            }
            
            return customerDTOList;
        }
        finally{
            em.close(); 
        }
    }

    public BankCustomer addCustomer(BankCustomer cust) {
        //String firstName, String lastName, String accountNumber, double balance, int customerRanking, String internalInfo
        String firstName = cust.getFirstName(); 
        String lastName = cust.getLastName(); 
        String accountNumber = cust.getAccountNumber(); 
        double balance = cust.getBalance(); 
        int customerRanking = cust.getCustomerRanking(); 
        String internalInfo = cust.getInternalInfo(); 

        BankCustomer customer = new BankCustomer(firstName, lastName, accountNumber, balance, customerRanking, internalInfo);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            return customer;
        } finally {
            em.close();
        }
    }
    
    

    public List<BankCustomer> getAllBankCustomers() {
         EntityManager em = emf.createEntityManager(); 
        try{
            Query query2 = em.createQuery("Select e FROM BankCustomer e ");
                   
            List<BankCustomer> customerList = query2.getResultList(); 
          
            return customerList;
        }
        finally{
            em.close(); 
        }
    }

}
