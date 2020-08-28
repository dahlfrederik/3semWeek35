package rest;

import com.google.gson.Gson;
import dto.EmployeeDTO;
import entities.Employee;
import facades.EmployeeFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("employee")
public class EmployeeResource {

    //NOTE: Change Persistence unit name according to your setup
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    EmployeeFacade facade = EmployeeFacade.getEmployeeFacade(emf);

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("all")
    public String allEmployees() {
        EntityManager em = emf.createEntityManager();
        try {
            List<EmployeeDTO> dtoList = new ArrayList();
            List<Employee> emp = facade.getAllEmployees();
            for (Employee employee : emp) {
                EmployeeDTO empDTO = new EmployeeDTO(employee);
                dtoList.add(empDTO);
            }
            return new Gson().toJson(dtoList);
        } catch (javax.persistence.NoResultException e) {
            String errorString = "Seems like the database is empty, you should insert some data";
            return new Gson().toJson(errorString);
        } finally {
            em.close();
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("byid/{id}")
    public String employeeById(@PathParam("id") int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Employee emp = facade.getEmployeeByID(id);
            EmployeeDTO dtoEmp = new EmployeeDTO(emp);
            return new Gson().toJson(dtoEmp);
        } catch (javax.persistence.NoResultException e) {
            String errorString = "The id " + id + " is not in the database and therefore this program cannot show you the result";
            return new Gson().toJson(errorString);
        }finally {
            em.close();
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("highestpaid")
    public String highestPaidEmployee() {
        EntityManager em = emf.createEntityManager();
        try {
            Employee emp = facade.getEmployeesWithHighestSalary();
            EmployeeDTO dtoEmp = new EmployeeDTO(emp);
            return new Gson().toJson(dtoEmp);
        } catch (javax.persistence.NoResultException e) {
            String errorString = "Seems like the database is empty, you should insert some data";
            return new Gson().toJson(errorString);
        } finally {
            em.close();
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("byname/{name}")
    public String employeeByName(@PathParam("name") String name) {
        EntityManager em = emf.createEntityManager();
        try {
            Employee emp = facade.getEmployeeByName(name);
            EmployeeDTO dtoEmp = new EmployeeDTO(emp);
            return new Gson().toJson(dtoEmp);
        } catch (javax.persistence.NoResultException e) {
            String errorString = "The name " + name + " is not in the database and therefore this program cannot show you the result";
            return new Gson().toJson(errorString);
        } finally {
            em.close();
        }
    }

}
