package hiberApp;

import model.Address;
import model.Course;
import model.Student;
import model.TheoryClass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class DataQueriesCriteriaAPI {

    public List<Tuple> getAddressesCityAndStreetWithCountryEquals(final EntityManagerFactory factory, final String country) {
        final EntityManager em = factory.createEntityManager();
        final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

        final CriteriaQuery<Tuple> query = criteriaBuilder.createQuery(Tuple.class);
        final Root<Address> root = query.from(Address.class);
        query.select(criteriaBuilder.construct(Tuple.class, root.get("city").alias("city"), root.get("street").alias("street")));
        query.where(criteriaBuilder.equal(root.get("country"), country));
        final List<Tuple> resultList = em.createQuery(query).getResultList();
        em.close();
        return resultList;
    }

    public List<Student> getStudentsWithUnpassedTheoryClasses(final EntityManagerFactory factory) {
        final EntityManager em = factory.createEntityManager();
        final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

        final CriteriaQuery<Student> query = criteriaBuilder.createQuery(Student.class);
        final Root<TheoryClass> root = query.from(TheoryClass.class);
        final Join<TheoryClass, Student> student = root.join("student");

        query.select(student)
                .where(criteriaBuilder.equal(root.get("grade"), 2))
                .distinct(true);

        final List<Student> resultList = em.createQuery(query).getResultList();
        em.close();
        return resultList;
    }

    public List<Course> getCoursesWithLessThanStudents(final EntityManagerFactory factory, int amount) {
        final EntityManager em = factory.createEntityManager();
        final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

        final CriteriaQuery<Course> query = criteriaBuilder.createQuery(Course.class);
        final Root<Course> root = query.from(Course.class);

        query.select(root)
                .where(criteriaBuilder.lessThan(criteriaBuilder.size(root.get("students")), amount));
        query.groupBy(root.get("id"));

        final List<Course> resultList = em.createQuery(query).getResultList();
        em.close();
        return resultList;
    }
}
