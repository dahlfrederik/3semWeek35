package facades;

import entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Frederik Dahl <cph-fd76@cphbusiness.dk>
 */
public class EmployeeFacade {

    private static EntityManagerFactory emf;
    private static EmployeeFacade instance;
    
    private EmployeeFacade(){    
    }


    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }
    
 
    public Employee getEmployeeByID(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query2 = em.createQuery("Select e FROM Employee e WHERE e.id = :id");
            query2.setParameter("id", id);
            Employee emp = (Employee) query2.getSingleResult();
            return emp;
        } finally {
            em.close();
        }
    }

    public Employee getEmployeeByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query2 = em.createQuery("Select e FROM Employee e WHERE e.name = :name");
            query2.setParameter("name", name);
            Employee emp = (Employee) query2.getSingleResult();
            return emp;
        } finally {
            em.close();
        }
    }

    public List<Employee> getAllEmployees() {
        EntityManager em = emf.createEntityManager();
        try {
            List<Employee> employeeList = em.createQuery("SELECT e FROM Employee e").getResultList();
            return employeeList;
        } finally {
            em.close();
        }
    }

    public Employee getEmployeesWithHighestSalary() {
        EntityManager em = emf.createEntityManager();
        try {
            Query query2 = em.createQuery("Select e FROM Employee e WHERE e.salary = (SELECT MAX(e.salary) FROM Employee e)");
            Employee emp = (Employee) query2.getSingleResult();
            return emp;
        } finally {
            em.close();
        }
    }

    public Employee createEmployee(String name, String adress, int salary) {
        Employee emp = new Employee(name, adress, salary);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(emp);
            em.getTransaction().commit();
            return emp;
        } finally {
            em.close();
        }
    }

    
    //BONUS METODER 
    public int getNumberOfEmployees() {
        EntityManager em = emf.createEntityManager();
        try {
            List<Employee> employeeList = em.createQuery("SELECT e FROM Employee e").getResultList();
            return employeeList.size();
        } finally {
            em.close();
        }
    }

    public void deleteEmployee(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.find(Employee.class, id));
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }
    
}
