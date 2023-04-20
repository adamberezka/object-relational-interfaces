package pl.polsl.ior.spring.persistance;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.ior.spring.persistance.address.entity.AddressEntity;
import pl.polsl.ior.spring.persistance.course.SpringDataJPACourseEntityDAO;
import pl.polsl.ior.spring.persistance.course.entity.CourseEntity;
import pl.polsl.ior.spring.persistance.flight.SpringDataJPAFlightEntityDAO;
import pl.polsl.ior.spring.persistance.flight.conversion.OffsetDateTimeConverter;
import pl.polsl.ior.spring.persistance.flight.entity.FlightEntity;
import pl.polsl.ior.spring.persistance.flightinstructor.SpringDataJPAFlightInstructorEntityDAO;
import pl.polsl.ior.spring.persistance.flightinstructor.entity.FlightInstructorEntity;
import pl.polsl.ior.spring.persistance.student.SpringDataJPAStudentEntityDAO;
import pl.polsl.ior.spring.persistance.student.entity.StudentEntity;
import pl.polsl.ior.spring.persistance.theoryclass.SpringDataJPATheoryClassEntityDAO;
import pl.polsl.ior.spring.persistance.theoryclass.entity.TheoryClassEntity;

import java.sql.Date;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataLoader {

    private final SpringDataJPAFlightEntityDAO flightEntityDAO;
    private final SpringDataJPAStudentEntityDAO studentEntityDAO;
    private final SpringDataJPACourseEntityDAO courseEntityDAO;
    private final SpringDataJPATheoryClassEntityDAO theoryClassEntityDAO;
    private final SpringDataJPAFlightInstructorEntityDAO flightInstructorEntityDAO;

    @Transactional
    public void loadInitialData() {

        final FlightEntity flight1 = createFlight(2, "desc BARREL roll");
        final FlightEntity flight2 = createFlight(3, "descflight2",
                OffsetDateTime.of(2022, 12, 8, 12, 12, 12, 0, ZoneOffset.UTC));
        final FlightEntity flight3 = createFlight(2, "descedesc",
                OffsetDateTime.of(2022, 12, 18, 12, 12, 12, 0, ZoneOffset.UTC));
        final FlightEntity flight4 = createFlight(1, "cztery");
        final FlightEntity flight5 = createFlight(2, "Flight Barrel number 5",
                OffsetDateTime.of(2022, 11, 12, 12, 12, 12, 0, ZoneOffset.UTC));
        final FlightEntity flight6 = createFlight(12, "Flight number 6",
                OffsetDateTime.of(2022, 11, 12, 12, 12, 12, 0, ZoneOffset.UTC));
        final FlightEntity flight7 = createFlight(3, "Flight number baRRel 7",
                OffsetDateTime.of(2022, 11, 8, 12, 12, 12, 0, ZoneOffset.UTC));
        flightEntityDAO.saveAll(List.of(flight1, flight2, flight3, flight4, flight5, flight6, flight7));

        final StudentEntity student1 = new StudentEntity();
        student1.setMedicalTests("Test medyczny studenta 1 oCzy");
        student1.setSSN("SSN1");
        student1.setFirstname("John");
        student1.setSurname("Wick");
        final AddressEntity address1 = createAddress("blumen", "Germany", "Berlin", "44-100");
        student1.setAddress(address1);
        student1.setFlights(Set.of(flight1, flight2));
        studentEntityDAO.save(student1);

        final StudentEntity student2 = new StudentEntity();
        student2.setMedicalTests("Studnet 2 test na wzrok i oCZY");
        student2.setSSN("SSN2");
        student2.setFirstname("Marek");
        student2.setSurname("Kowalski");
        final AddressEntity address2 = createAddress("piastow", "Poland", "Katowice", "44-420");
        student2.setAddress(address2);
        student2.setFlights(Set.of(flight3, flight4));
        studentEntityDAO.save(student2);

        final StudentEntity student3 = new StudentEntity();
        student3.setMedicalTests("Studnet 3 med test i oczy");
        student3.setSSN("SSN3");
        student3.setFirstname("Zbigniew");
        student3.setSurname("Bogdan");
        final AddressEntity address5 = createAddress("zwyciestaw", "Poland", "Gliwice", "44-100");
        student3.setAddress(address5);
        student3.setFlights(Set.of(flight5, flight6, flight7));
        studentEntityDAO.save(student3);

        final StudentEntity student4 = new StudentEntity();
        student4.setMedicalTests("Skin test xdd 111");
        student4.setSSN("SSN4 1");
        student4.setFirstname("Michael");
        student4.setSurname("Jackson");
        final AddressEntity address6 = createAddress("Bkaer", "UK", "London", "10938");
        student4.setAddress(address6);
        student4.setFlights(Set.of());
        studentEntityDAO.save(student4);

        final StudentEntity student5 = new StudentEntity();
        student5.setMedicalTests("Skin test xdd 222");
        student5.setSSN("SSN4 2");
        student5.setFirstname("Michael");
        student5.setSurname("Jackson");
        final AddressEntity address7 = createAddress("Bkaer", "UK", "London", "10938");
        student5.setAddress(address7);
        student5.setFlights(Set.of());
        studentEntityDAO.save(student5);

        final StudentEntity student6 = new StudentEntity();
        student6.setMedicalTests("Skin test xdd 333");
        student6.setSSN("SSN4 3");
        student6.setFirstname("Michael");
        student6.setSurname("Jackson");
        final AddressEntity address8 = createAddress("Bkaer", "UK", "London", "10938");
        student6.setAddress(address8);
        student6.setFlights(Set.of());
        studentEntityDAO.save(student6);

        final StudentEntity student7 = new StudentEntity();
        student7.setMedicalTests("Skin test xdd 444");
        student7.setSSN("SSN4 4");
        student7.setFirstname("Michael");
        student7.setSurname("Jackson");
        final AddressEntity address9 = createAddress("Bkaer", "UK", "London", "10938");
        student7.setAddress(address9);
        student7.setFlights(Set.of());
        studentEntityDAO.save(student7);

        final StudentEntity student8 = new StudentEntity();
        student8.setMedicalTests("Skin test xdd 444");
        student8.setSSN("SSN4 4");
        student8.setFirstname("Jason");
        student8.setSurname("Jackson");
        final AddressEntity address11 = createAddress("Bkaer", "UK", "London", "10938");
        student8.setAddress(address11);
        student8.setFlights(Set.of());
        studentEntityDAO.save(student8);


        final TheoryClassEntity theoryClass1 = createTheoryClass(student1, 5, 3, "TH1-1");
        theoryClassEntityDAO.save(theoryClass1);
        final TheoryClassEntity theoryClass2 = createTheoryClass(student1, 6, 2, "TH1-2");
        theoryClassEntityDAO.save(theoryClass2);
        final TheoryClassEntity theoryClass3 = createTheoryClass(student1, 90, 2, "TH1-3");
        theoryClassEntityDAO.save(theoryClass3);
        final TheoryClassEntity theoryClass4 = createTheoryClass(student2, 2, 3, "TH2-1");
        theoryClassEntityDAO.save(theoryClass4);
        final TheoryClassEntity theoryClass5 = createTheoryClass(student2, 5, 4, "TH2-2");
        theoryClassEntityDAO.save(theoryClass5);
        final TheoryClassEntity theoryClass6 = createTheoryClass(student3, 22, 5, "TH3-1");
        theoryClassEntityDAO.save(theoryClass6);


        final FlightInstructorEntity flightInstructor1 = new FlightInstructorEntity();
        flightInstructor1.setSSN("SSN fl 1");
        flightInstructor1.setFirstname("Maciek");
        flightInstructor1.setSurname("Instruktor");
        flightInstructor1.setLicenceNo(1234);
        flightInstructor1.setValid(true);
        final AddressEntity address3 = createAddress("ulica", "Poland", "Poznań", "413-2112");
        flightInstructor1.setAddress(address3);
        flightInstructor1.setFlights(Set.of(flight1, flight2));
        flightInstructorEntityDAO.save(flightInstructor1);

        final FlightInstructorEntity flightInstructor2 = new FlightInstructorEntity();
        flightInstructor2.setSSN("SSN instruktor 2");
        flightInstructor2.setFirstname("Karol");
        flightInstructor2.setSurname("Karolak");
        flightInstructor2.setLicenceNo(56789);
        flightInstructor2.setValid(false);
        final AddressEntity address4 = createAddress("street", "UK", "London", "11/2334");
        flightInstructor2.setAddress(address4);
        flightInstructor2.setFlights(Set.of(flight3, flight4));
        flightInstructorEntityDAO.save(flightInstructor2);

        final FlightInstructorEntity flightInstructor3 = new FlightInstructorEntity();
        flightInstructor3.setSSN("SSN instruktor trzy");
        flightInstructor3.setFirstname("Bogdan");
        flightInstructor3.setSurname("Adrian");
        flightInstructor3.setLicenceNo(123344);
        flightInstructor3.setValid(true);
        final AddressEntity address10 = createAddress("first", "USA", "Boston", "10987");
        flightInstructor3.setAddress(address10);
        flightInstructor3.setFlights(Set.of(flight5, flight6, flight7));
        flightInstructorEntityDAO.save(flightInstructor3);


        final CourseEntity course1 = createCourse("Basic", "Low Level course . . .",
                OffsetDateTime.of(2022, 11, 8, 12, 0, 0, 0, ZoneOffset.UTC),
                OffsetDateTime.of(2022, 12, 8, 12, 0, 0, 0, ZoneOffset.UTC));
        course1.setStudents(Set.of(student1));
        final CourseEntity course2 = createCourse("Professional", "High level course . . .");
        course2.setStudents(Set.of(student1, student2, student3));
        final CourseEntity course3 = createCourse("Basic", "Basic LEVEL course . . .",
                OffsetDateTime.of(2022, 10, 18, 12, 0, 0, 0, ZoneOffset.UTC),
                OffsetDateTime.of(2022, 11, 18, 12, 0, 0, 0, ZoneOffset.UTC));
        course3.setStudents(Set.of(student1, student3));
        final CourseEntity course4 = createCourse("Professional Plus", "Highest course . . .");
        course4.setStudents(Set.of(student2));
        final CourseEntity course5 = createCourse("Basic", "Lowest  course . . .");
        course5.setStudents(Set.of());
        final CourseEntity course6 = createCourse("Rookie", "Lowest  course .1.");
        course6.setStudents(Set.of());
        final CourseEntity course7 = createCourse("Rookie", "Lowest  course .2 .");
        course7.setStudents(Set.of());
        final CourseEntity course8 = createCourse("Rookie", "Lowest  course . 3 .");
        course8.setStudents(Set.of());
        final CourseEntity course9 = createCourse("Rookie", "Lowest  course . 4 .");
        course9.setStudents(Set.of());
        courseEntityDAO.save(course1);
        courseEntityDAO.save(course2);
        courseEntityDAO.save(course3);
        courseEntityDAO.save(course4);
        courseEntityDAO.save(course5);
        courseEntityDAO.save(course6);
        courseEntityDAO.save(course7);
        courseEntityDAO.save(course8);
        courseEntityDAO.save(course9);
    }

    private static FlightEntity createFlight(final int hours, final String description) {
        final FlightEntity flight = new FlightEntity();
        flight.setHours(hours);
        flight.setDescription(description);
        flight.setDate(Date.valueOf(OffsetDateTime.now().toLocalDate()));
        return flight;
    }

    private static FlightEntity createFlight(final int hours, final String description, final OffsetDateTime date) {
        final FlightEntity flight = createFlight(hours, description);
        flight.setDate(OffsetDateTimeConverter.toDate(date));
        return flight;
    }

    private static TheoryClassEntity createTheoryClass(final StudentEntity student, final int hours, final int grade, final String name) {
        final TheoryClassEntity theoryClass = new TheoryClassEntity();
        theoryClass.setName(name);
        theoryClass.setGrade(grade);
        theoryClass.setHours(hours);
        theoryClass.setStudent(student);
        return theoryClass;
    }

    private static CourseEntity createCourse(final String type, final String description) {
        final CourseEntity course = new CourseEntity();
        course.setCertType(type);
        course.setDescription(description);
        course.setStartDate(OffsetDateTime.now());
        course.setEndDate(OffsetDateTime.now().plusMonths(new Random().nextInt() % 10));
        return course;
    }

    private static CourseEntity createCourse(final String type, final String description, final OffsetDateTime startDate, final OffsetDateTime endDate) {
        final CourseEntity course = createCourse(type, description);
        course.setStartDate(startDate);
        course.setEndDate(endDate);
        return course;
    }

    private static AddressEntity createAddress(final String street, final String country, final String city, final String postalCode) {
        final AddressEntity address = new AddressEntity();
        address.setStreet(street);
        address.setCountry(country);
        address.setCity(city);
        address.setPostalCode(postalCode);
        return address;
    }
}
