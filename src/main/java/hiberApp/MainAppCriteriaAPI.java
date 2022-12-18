package hiberApp;

import model.Address;
import model.Course;
import model.Student;
import org.hibernate.SessionFactory;
import util.HiberUtil;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Tuple;
import java.util.List;


public final class MainAppCriteriaAPI {

    private final static EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("labPU");
    private static final SessionFactory SESSION_FACTORY = HiberUtil.getSessionFactory(HiberUtil.Mapping.ANN);

    public static void main(String[] args) {

        final DataLoad dataLoad = new DataLoad();
        dataLoad.createData(SESSION_FACTORY);

        final DataQueriesCriteriaAPI dataQueries = new DataQueriesCriteriaAPI();

        final String country = "Poland";
        System.out.println();
        System.out.println("City + street from addresses where country=" + country);
        final List<Tuple> addresses = dataQueries.getAddressesCityAndStreetWithCountryEquals(FACTORY, country);
        addresses.forEach(address ->
                System.out.println("City: " + address.get("city") + ", street: " + address.get("street")));

        System.out.println();
        System.out.println("Students that have theory class equal 2");
        final List<Student> students = dataQueries.getStudentsWithUnpassedTheoryClasses(FACTORY);
        students.forEach(student -> System.out.println(student.getFName() + " " + student.getSName()));


        final int amount = 2;
        System.out.println();
        System.out.println("Courses with less than " + amount + " students");
        final List<Course> courses = dataQueries.getCoursesWithLessThanStudents(FACTORY, amount);
        courses.forEach(System.out::println);

        FACTORY.close();
    }
}
