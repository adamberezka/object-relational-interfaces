package hiberApp;

import model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.OffsetDateTime;
import java.util.Random;

public final class DataLoad {

    static Logger log = LoggerFactory.getLogger(DataLoad.class);

    public void createData(final SessionFactory SESSION_FACTORY) {

        try (Session session = SESSION_FACTORY.openSession()) {

            Transaction tx = null;
            try {
                tx = session.beginTransaction();


                final Course course1 = createCourse("Rookie", "Low level course . . .");
                final Course course2 = createCourse("Professional", "High level course . . .");
                final Course course3 = createCourse("Basic", "Basic level course . . .");
                final Course course4 = createCourse("Professional Plus", "Highest level course . . .");
                final Course course5 = createCourse("VeryBasic", "Lowest level course . . .");
                session.save(course5);


                final Student student1 = new Student();
                student1.setMedicalTests("Test medyczny studenta 1");
                student1.setSSN("SSN1");
                student1.setFName("John");
                student1.setSName("Wick");
                final Address address1 = createAddress("blumen", "Germany", "Berlin", "44-100");
                student1.setAddress(address1);
                student1.getCourses().add(course1);
                student1.getCourses().add(course4);
                session.save(student1);

                final Student student2 = new Student();
                student2.setMedicalTests("Studnet 2 test na wzrok");
                student2.setSSN("SSN2");
                student2.setFName("Marek");
                student2.setSName("Kowalski");
                final Address address2 = createAddress("piastow", "Poland", "Katowice", "44-420");
                student2.setAddress(address2);
                student2.getCourses().add(course2);
                student2.getCourses().add(course3);
                session.save(student2);

                final Student student3 = new Student();
                student3.setMedicalTests("Studnet 3 med test");
                student3.setSSN("SSN3");
                student3.setFName("Zbigniew");
                student3.setSName("Bogdan");
                final Address address5 = createAddress("zwyciestaw", "Poland", "Gliwice", "44-100");
                student3.setAddress(address5);
                student3.getCourses().add(course2);
                student3.getCourses().add(course1);
                student3.getCourses().add(course3);
                session.save(student3);


                final TheoryClass theoryClass1 = createTheoryClass(student1, 5, 3, "TH1-1");
                session.save(theoryClass1);
                final TheoryClass theoryClass2 = createTheoryClass(student1, 6, 2, "TH1-2");
                session.save(theoryClass2);
                final TheoryClass theoryClass3 = createTheoryClass(student1, 90, 2, "TH1-3");
                session.save(theoryClass3);
                final TheoryClass theoryClass4 = createTheoryClass(student2, 2, 3, "TH2-1");
                session.save(theoryClass4);
                final TheoryClass theoryClass5 = createTheoryClass(student2, 5, 4, "TH2-2");
                session.save(theoryClass5);
                final TheoryClass theoryClass6 = createTheoryClass(student3, 22, 5, "TH3-1");
                session.save(theoryClass6);


                final FlightInstructor flightInstructor1 = new FlightInstructor();
                flightInstructor1.setSSN("SSN fl 1");
                flightInstructor1.setFName("Maciek");
                flightInstructor1.setSName("Instruktor");
                flightInstructor1.setLicenceNo(1234);
                flightInstructor1.setValid(true);
                final Address address3 = createAddress("ulica", "Poland", "Poznań", "413-2112");
                flightInstructor1.setAddress(address3);
                session.save(flightInstructor1);

                final FlightInstructor flightInstructor2 = new FlightInstructor();
                flightInstructor2.setSSN("SSN instruktor 2");
                flightInstructor2.setFName("Karol");
                flightInstructor2.setSName("Karolak");
                flightInstructor2.setLicenceNo(56789);
                flightInstructor2.setValid(false);
                final Address address4 = createAddress("street", "UK", "London", "11/2334");
                flightInstructor2.setAddress(address4);
                session.save(flightInstructor2);

                final FlightInstructor flightInstructor3 = new FlightInstructor();
                flightInstructor3.setSSN("SSN instruktor trzy");
                flightInstructor3.setFName("Bogdan");
                flightInstructor3.setSName("Adrian");
                flightInstructor3.setLicenceNo(123344);
                flightInstructor3.setValid(true);
                final Address address6 = createAddress("first", "USA", "Boston", "10987");
                flightInstructor3.setAddress(address6);
                session.save(flightInstructor3);


                final Flight flight1 = createFlight(2, "desc", student1, flightInstructor1);
                session.save(flight1);
                final Flight flight2 = createFlight(3, "descflight2", student1, flightInstructor2);
                session.save(flight2);
                final Flight flight3 = createFlight(12, "descedesc", student2, flightInstructor2);
                session.save(flight3);
                final Flight flight4 = createFlight(1, "cztery", student2, flightInstructor2);
                session.save(flight4);
                final Flight flight5 = createFlight(133, "Flight number 5", student2, flightInstructor1);
                session.save(flight5);
                final Flight flight6 = createFlight(12, "Flight number 6", student3, flightInstructor3);
                session.save(flight6);
                final Flight flight7 = createFlight(3, "Flight number 7", student1, flightInstructor3);
                session.save(flight7);

                tx.commit();

            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                    log.trace(e.toString());
                }
            }
        }
    }

    private static Flight createFlight(final int hours, final String description, final Student student, final FlightInstructor flightInstructor) {
        final Flight flight = new Flight();
        flight.setHours(hours);
        flight.setDescription(description);
        flight.setDate(OffsetDateTime.now().plusDays(new Random().nextInt() % 10));
        flight.setStudent(student);
        flight.setFlightInstructor(flightInstructor);
        return flight;
    }

    private static TheoryClass createTheoryClass(final Student student, final int hours, final int grade, final String name) {
        final TheoryClass theoryClass = new TheoryClass();
        theoryClass.setName(name);
        theoryClass.setGrade(grade);
        theoryClass.setHours(hours);
        theoryClass.setStudent(student);
        return theoryClass;
    }

    private static Course createCourse(final String type, final String description) {
        final Course course = new Course();
        course.setCertType(type);
        course.setDescription(description);
        course.setStartDate(OffsetDateTime.now());
        course.setEndDate(OffsetDateTime.now().plusMonths(new Random().nextInt() % 10));
        return course;
    }

    private static Address createAddress(final String street, final String country, final String city, final String postalCode){
        final Address address = new Address();
        address.setStreet(street);
        address.setCountry(country);
        address.setCity(city);
        address.setPostalCode(postalCode);
        return address;
    }
}
