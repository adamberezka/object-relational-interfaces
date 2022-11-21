package hiberApp;

import model.old.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;


public final class MainAppJPA {

    private final static EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("labPU");

    public static void main(String[] args) {
        testJPQL();
        FACTORY.close();
    }

    public static void testJPQL() {
        EntityManager em = FACTORY.createEntityManager();
        List<Employee> result = em.createQuery("select e from Employee e").getResultList();
        result.forEach(System.out::println);
        System.out.println("=======================");
        em.close();
    }

//    public static void showAllEmployees() {
//        EntityManager em = EMBuilder.getEM();
//        List<Employee> result = em.createQuery("select e from Employee e").getResultList();
//        result.forEach(System.out::println);
//        System.out.println("=======================");
//        CriteriaBuilder builder = em.getCriteriaBuilder();
//        CriteriaQuery criteria = builder.createQuery(Employee.class);
//        Root<Employee> root = criteria.from(Employee.class);
//        criteria.select(root);
//        result = em.createQuery(criteria).getResultList();
//        result.forEach(System.out::println);
//
//        builder = em.getCriteriaBuilder();
//        criteria = builder.createQuery(String.class);
//        root = criteria.from(Employee.class);
//        criteria.select(root.get(Employee_.NAME));
//        List<String> names = em.createQuery(criteria).getResultList();
//        names.forEach(System.out::println);
//        em.close();
//    }

}
