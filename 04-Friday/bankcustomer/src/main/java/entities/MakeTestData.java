/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import facades.CustomerFacade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 
 * @author Frederik Dahl <cph-fd76@cphbusiness.dk>
 */
public class MakeTestData {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        //String firstName, String lastName, String accountNumber, double balance, int customerRanking, String internalInfo
        try{
            em.getTransaction().begin();
            em.persist(new BankCustomer("Thor","Christensen", "f123fa", 20000.0, 2, "betaler ikke til tiden"));
            em.persist(new BankCustomer("Josef","Marc", "g123ga", 10000.0, 3, "skylder 200k"));
            em.persist(new BankCustomer("Frederik","Dahl", "fbd102", 50000.0, 1, "betaler til tiden"));
            em.getTransaction().commit(); 
            
        }finally{
            em.close();
            emf.close();
        }
    }
}
    
