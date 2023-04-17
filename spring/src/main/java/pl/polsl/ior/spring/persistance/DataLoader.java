package pl.polsl.ior.spring.persistance;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.ior.spring.persistance.address.entity.AddressEntity;
import pl.polsl.ior.spring.persistance.course.SpringDataJPACourseEntityDAO;
import pl.polsl.ior.spring.persistance.course.entity.CourseEntity;
import pl.polsl.ior.spring.persistance.flight.SpringDataJPAFlightEntityDAO;
import pl.polsl.ior.spring.persistance.flight.entity.FlightEntity;
import pl.polsl.ior.spring.persistance.flightinstructor.SpringDataJPAFlightInstructorEntityDAO;
import pl.polsl.ior.spring.persistance.flightinstructor.entity.FlightInstructorEntity;
import pl.polsl.ior.spring.persistance.student.SpringDataJPAStudentEntityDAO;
import pl.polsl.ior.spring.persistance.student.entity.StudentEntity;
import pl.polsl.ior.spring.persistance.theoryclass.SpringDataJPATheoryClassEntityDAO;
import pl.polsl.ior.spring.persistance.theoryclass.entity.TheoryClassEntity;

import java.time.OffsetDateTime;
import java.util.Random;

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

        final CourseEntity course1 = createCourse("Rookie", "Low level course . . .");
        final CourseEntity course2 = createCourse("Professional", "High level course . . .");
        final CourseEntity course3 = createCourse("Basic", "Basic level course . . .");
        final CourseEntity course4 = createCourse("Professional Plus", "Highest level course . . .");
        final CourseEntity course5 = createCourse("VeryBasic", "Lowest level course . . .");
        courseEntityDAO.save(course5);

        final StudentEntity student1 = new StudentEntity();
        student1.setMedicalTests("Test medyczny studenta 1");
        student1.setSSN("SSN1");
        student1.setFirstname("John");
        student1.setSurname("Wick");
        final AddressEntity address1 = createAddress("blumen", "Germany", "Berlin", "44-100");
        student1.setAddress(address1);
        studentEntityDAO.save(student1);

        final StudentEntity student2 = new StudentEntity();
        student2.setMedicalTests("Studnet 2 test na wzrok");
        student2.setSSN("SSN2");
        student2.setFirstname("Marek");
        student2.setSurname("Kowalski");
        final AddressEntity address2 = createAddress("piastow", "Poland", "Katowice", "44-420");
        student2.setAddress(address2);
        studentEntityDAO.save(student2);

        final StudentEntity student3 = new StudentEntity();
        student3.setMedicalTests("Studnet 3 med test");
        student3.setSSN("SSN3");
        student3.setFirstname("Zbigniew");
        student3.setSurname("Bogdan");
        final AddressEntity address5 = createAddress("zwyciestaw", "Poland", "Gliwice", "44-100");
        student3.setAddress(address5);
        studentEntityDAO.save(student3);


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
        flightInstructorEntityDAO.save(flightInstructor1);

        final FlightInstructorEntity flightInstructor2 = new FlightInstructorEntity();
        flightInstructor2.setSSN("SSN instruktor 2");
        flightInstructor2.setFirstname("Karol");
        flightInstructor2.setSurname("Karolak");
        flightInstructor2.setLicenceNo(56789);
        flightInstructor2.setValid(false);
        final AddressEntity address4 = createAddress("street", "UK", "London", "11/2334");
        flightInstructor2.setAddress(address4);
        flightInstructorEntityDAO.save(flightInstructor2);

        final FlightInstructorEntity flightInstructor3 = new FlightInstructorEntity();
        flightInstructor3.setSSN("SSN instruktor trzy");
        flightInstructor3.setFirstname("Bogdan");
        flightInstructor3.setSurname("Adrian");
        flightInstructor3.setLicenceNo(123344);
        flightInstructor3.setValid(true);
        final AddressEntity address6 = createAddress("first", "USA", "Boston", "10987");
        flightInstructor3.setAddress(address6);
        flightInstructorEntityDAO.save(flightInstructor3);


        final FlightEntity flight1 = createFlight(2, "desc", student1, flightInstructor1);
        flightEntityDAO.save(flight1);
        final FlightEntity flight2 = createFlight(3, "descflight2", student1, flightInstructor2);
        flightEntityDAO.save(flight2);
        final FlightEntity flight3 = createFlight(12, "descedesc", student2, flightInstructor2);
        flightEntityDAO.save(flight3);
        final FlightEntity flight4 = createFlight(1, "cztery", student2, flightInstructor2);
        flightEntityDAO.save(flight4);
        final FlightEntity flight5 = createFlight(133, "Flight number 5", student2, flightInstructor1);
        flightEntityDAO.save(flight5);
        final FlightEntity flight6 = createFlight(12, "Flight number 6", student3, flightInstructor3);
        flightEntityDAO.save(flight6);
        final FlightEntity flight7 = createFlight(3, "Flight number 7", student1, flightInstructor3);
        flightEntityDAO.save(flight7);

    }

    private static FlightEntity createFlight(final int hours, final String description, final StudentEntity student, final FlightInstructorEntity flightInstructor) {
        final FlightEntity flight = new FlightEntity();
        flight.setHours(hours);
        flight.setDescription(description);
        flight.setDate(OffsetDateTime.now().plusDays(new Random().nextInt() % 10));
        flight.setStudent(student);
        flight.setFlightInstructor(flightInstructor);
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

    private static AddressEntity createAddress(final String street, final String country, final String city, final String postalCode){
        final AddressEntity address = new AddressEntity();
        address.setStreet(street);
        address.setCountry(country);
        address.setCity(city);
        address.setPostalCode(postalCode);
        return address;
    }
}
