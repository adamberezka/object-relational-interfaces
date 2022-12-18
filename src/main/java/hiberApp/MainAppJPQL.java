package hiberApp;

import model.Course;
import model.Student;
import util.HiberUtil;
import org.hibernate.SessionFactory;

import javax.persistence.Tuple;
import java.util.List;


public final class MainAppJPQL {

    private static final SessionFactory SESSION_FACTORY = HiberUtil.getSessionFactory(HiberUtil.Mapping.ANN);

    public static void main(String[] args) {
        
        final DataLoad dataLoad = new DataLoad();
        dataLoad.createData(SESSION_FACTORY);
        final DataQueriesJPQL dataQueries = new DataQueriesJPQL();

        final String country = "Poland";
        System.out.println();
        System.out.println("City + street from addresses where country=" + country);
        final List<Tuple> addresses = dataQueries.getAddressesCityAndStreetWithCountryEquals(SESSION_FACTORY, country);
        addresses.forEach(address ->
                System.out.println("City: " + address.get("city") + ", street: " + address.get("street")));

        System.out.println();
        System.out.println("Students that have theory class equal 2");
        final List<Student> students = dataQueries.getStudentsWithUnpassedTheoryClasses(SESSION_FACTORY);
        students.forEach(student -> System.out.println(student.getFName() + " " + student.getSName()));

        final int amount = 2;
        System.out.println();
        System.out.println("Courses with less than " + amount + " students");
        final List<Course> courses = dataQueries.getCoursesWithLessThanStudents(SESSION_FACTORY, amount);
        courses.forEach(System.out::println);

    }
}
