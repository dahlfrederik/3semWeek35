/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import entities.Employee;
import facades.EmployeeFacade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Frederik Dahl <cph-fd76@cphbusiness.dk>
 */
public class InsertData {

    public void insertDataInDB(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        EmployeeFacade empfacade = EmployeeFacade.getEmployeeFacade(emf);
        empfacade.createEmployee("Josef Marc", "Glostrupvej", 25000);
        empfacade.createEmployee("Frederik Dahl", "Tagensvej", 1000000);
        empfacade.createEmployee("Thor Christensen", "Frederiksbergvej", 26000);
    }
    
    public static void main(String[] args) {
        new InsertData().insertDataInDB(); 
    }
}
