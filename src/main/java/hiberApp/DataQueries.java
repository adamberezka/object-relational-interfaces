package hiberApp;


import model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import java.util.List;

public final class DataQueries {

    public List<Tuple> getAddressesCityAndStreetWithCountryEquals(SessionFactory SESSION_FACTORY, String country) {
        try (Session session = SESSION_FACTORY.openSession()) {
            TypedQuery<Tuple> query = session.createQuery("" +
                    "select a.city as city, a.street as street from Address a " +
                    "where a.country=:country", Tuple.class);
            query.setParameter("country", country);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public List<Student> getStudentsWithUnpassedTheoryClasses(SessionFactory SESSION_FACTORY) {
        try (Session session = SESSION_FACTORY.openSession()) {
            Query query = session.createQuery("" +
                    "select s from Student s where s.id in " +
                    "   (select ss.id from Student ss join ss.theoryClasses tc " +
                    "   where tc.grade = 2)", Student.class);
            return query.list();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public List<Course> getCoursesWithLessThanStudents(SessionFactory SESSION_FACTORY, int amount) {
        try (Session session = SESSION_FACTORY.openSession()) {
            Query query = session.createQuery("" +
                    "select c from Course c left join c.students s " +
                    "group by c.id " +
                    "having COUNT(s.id) < :amount", Course.class);
            query.setParameter("amount", (long)amount);
            return query.list();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void showAll(SessionFactory SESSION_FACTORY) {
        try (Session session = SESSION_FACTORY.openSession()) {
            Query query = session.createQuery("select o from java.lang.Object o");
            query.setComment("All objects list");
            List<Object> all = query.list();
            all.forEach(System.out::println);
        }
    }
}
