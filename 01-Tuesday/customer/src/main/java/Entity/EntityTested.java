package Entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 
 * @author Frederik Dahl <cph-fd76@cphbusiness.dk>
 */
public class EntityTested {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        try{
            em.getTransaction().begin();
            Customer c1 = new Customer("Thor","Christensen");
            Customer c2 = new Customer("Josef","Marc");
            Customer c3 = new Customer("Frederik","Dahl");
            em.persist(c1);
            em.persist(c2);
            em.persist(c3);
            em.getTransaction().commit(); 
            
        }finally{
            em.close();
            emf.close();
        }
    }

}
