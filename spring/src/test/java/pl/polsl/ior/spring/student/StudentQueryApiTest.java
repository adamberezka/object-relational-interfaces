package pl.polsl.ior.spring.student;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import pl.polsl.ior.spring.api.Api;
import pl.polsl.ior.spring.api.ApiStudent;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class StudentQueryApiTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final OkHttpClient httpClient = new OkHttpClient();
    private final HttpUrl urlBase = HttpUrl.parse("http://localhost:8080" + Api.Student.ENDPOINT);

    @Test
    public void shouldGetAllStudents() throws IOException {

        final Request request = new Request.Builder()
                .url(urlBase)
                .build();

        final Call call = httpClient.newCall(request);
        final Response response = call.execute();
        final ResponseBody responseBody = response.body();

        final List<ApiStudent> students = objectMapper.readValue(responseBody.string(), new TypeReference<>() {});

        assertEquals(8, students.size());
    }

    @Test
    public void shouldFindStudentsByFirstNameAndSurname() throws IOException {

        final String firstNameSearchParameter = "John";
        final String surnameSearchParameter = "Wick";

        final HttpUrl url = urlBase.newBuilder()
                .addQueryParameter("firstName", firstNameSearchParameter)
                .addQueryParameter("surname", surnameSearchParameter)
                .build();

        final Request request = new Request.Builder()
                .url(url)
                .build();

        final Call call = httpClient.newCall(request);
        final Response response = call.execute();
        final ResponseBody responseBody = response.body();

        final List<ApiStudent> students = objectMapper.readValue(responseBody.string(), new TypeReference<>() {});

        assertEquals(1, students.size());
        assertEquals(firstNameSearchParameter, students.get(0).getFName());
        assertEquals(surnameSearchParameter, students.get(0).getSName());
    }

    @Test
    public void shouldFindStudentsByFirstNameAndSurnamePageable() throws IOException {

        final String firstNameSearchParameter = "Michael";
        final String surnameSearchParameter = "Jackson";
        final String firstPageNumber = "0";
        final String pageSize = "2";

        final HttpUrl firstPageUrl = urlBase.newBuilder()
                .addQueryParameter("firstName", firstNameSearchParameter)
                .addQueryParameter("surname", surnameSearchParameter)
                .addQueryParameter("pageNumber", firstPageNumber)
                .addQueryParameter("pageSize", pageSize)
                .build();

        final Request firstPageRequest = new Request.Builder()
                .url(firstPageUrl)
                .build();

        final Call firstPageCall = httpClient.newCall(firstPageRequest);
        final Response firstPageResponse = firstPageCall.execute();
        final ResponseBody firstPageResponseBody = firstPageResponse.body();

        final List<ApiStudent> firstPageStudents = objectMapper.readValue(firstPageResponseBody.string(), new TypeReference<>() {});
        assertEquals(Integer.valueOf(pageSize), firstPageStudents.size());

        // Second Page
        final String secondPageNumber = "1";

        final HttpUrl secondPageUrl = urlBase.newBuilder()
                .addQueryParameter("firstName", firstNameSearchParameter)
                .addQueryParameter("surname", surnameSearchParameter)
                .addQueryParameter("pageNumber", secondPageNumber)
                .addQueryParameter("pageSize", pageSize)
                .build();

        final Request secondPageRequest = new Request.Builder()
                .url(secondPageUrl)
                .build();

        final Call secondPageCall = httpClient.newCall(secondPageRequest);
        final Response secondPageResponse = secondPageCall.execute();
        final ResponseBody secondPageResponseBody = secondPageResponse.body();

        final List<ApiStudent> secondPageStudents = objectMapper.readValue(secondPageResponseBody.string(), new TypeReference<>() {});
        assertEquals(Integer.valueOf(pageSize), secondPageStudents.size());

        assertTrue(secondPageStudents.stream().noneMatch(apiStudent ->
                apiStudent.getId().equals(firstPageStudents.get(0).getId())));
        assertTrue(secondPageStudents.stream().noneMatch(apiStudent ->
                apiStudent.getId().equals(firstPageStudents.get(1).getId())));
        assertTrue(firstPageStudents.stream().noneMatch(apiStudent ->
                apiStudent.getId().equals(secondPageStudents.get(0).getId())));
        assertTrue(firstPageStudents.stream().noneMatch(apiStudent ->
                apiStudent.getId().equals(secondPageStudents.get(1).getId())));
    }

    @Test
    public void shouldFindByFirstNameLike() throws IOException {

        final String firstNameLikeSearchParameter = "J%";

        final HttpUrl url = urlBase.newBuilder()
                .addQueryParameter("firstNameLike", firstNameLikeSearchParameter)
                .build();

        final Request request = new Request.Builder()
                .url(url)
                .build();

        final Call call = httpClient.newCall(request);
        final Response response = call.execute();
        final ResponseBody responseBody = response.body();

        final List<ApiStudent> students = objectMapper.readValue(responseBody.string(), new TypeReference<>() {});

        assertEquals(2, students.size());
        assertTrue(students.stream().allMatch(apiStudent -> apiStudent.getFName().startsWith("J")));
    }

    @Test
    public void shouldFindDistinctByFirstName() throws IOException {

        final String firstNameDistinctSearchParameter = "Michael";

        final HttpUrl url = urlBase.newBuilder()
                .addQueryParameter("distinctFirstName", firstNameDistinctSearchParameter)
                .build();

        final Request request = new Request.Builder()
                .url(url)
                .build();

        final Call call = httpClient.newCall(request);
        final Response response = call.execute();
        final ResponseBody responseBody = response.body();

        final List<ApiStudent> students = objectMapper.readValue(responseBody.string(), new TypeReference<>() {});

        assertEquals(4, students.size());
        assertEquals(firstNameDistinctSearchParameter, students.get(0).getFName());
    }

    @Test
    public void shouldFindByFirstNameOrSurname() throws IOException {

        final String firstNameOrParameter = "John";
        final String surnameOrParameter = "Kowalski";

        final HttpUrl url = urlBase.newBuilder()
                .addQueryParameter("orFirstName", firstNameOrParameter)
                .addQueryParameter("orSurname", surnameOrParameter)
                .build();

        final Request request = new Request.Builder()
                .url(url)
                .build();

        final Call call = httpClient.newCall(request);
        final Response response = call.execute();
        final ResponseBody responseBody = response.body();

        final List<ApiStudent> students = objectMapper.readValue(responseBody.string(), new TypeReference<>() {});

        assertEquals(2, students.size());
        assertTrue(students.stream().anyMatch(apiStudent -> apiStudent.getFName().equals(firstNameOrParameter)));
        assertTrue(students.stream().anyMatch(apiStudent -> apiStudent.getSName().equals(surnameOrParameter)));
    }

    @Test
    public void shouldFindByMedicalTestsContaining() throws IOException {

        final String medicalTestsContainSearchParameter = "OCZY";

        final HttpUrl url = urlBase.newBuilder()
                .addQueryParameter("medicalTestsContain", medicalTestsContainSearchParameter)
                .build();

        final Request request = new Request.Builder()
                .url(url)
                .build();

        final Call call = httpClient.newCall(request);
        final Response response = call.execute();
        final ResponseBody responseBody = response.body();

        final List<ApiStudent> students = objectMapper.readValue(responseBody.string(), new TypeReference<>() {});

        assertEquals(3, students.size());
        assertTrue(students.stream().anyMatch(apiStudent -> apiStudent.getId().equals(1L)));
        assertTrue(students.stream().anyMatch(apiStudent -> apiStudent.getId().equals(2L)));
        assertTrue(students.stream().anyMatch(apiStudent -> apiStudent.getId().equals(3L)));
    }

    @Test
    public void shouldFindWithFlightsBetween() throws IOException {

        final String fromSearchParameter =
                OffsetDateTime.of(2022, 11, 1, 0, 0, 0, 0, ZoneOffset.UTC).toString();
        final String toSearchParameter =
                OffsetDateTime.of(2022, 12, 31, 0, 0, 0, 0, ZoneOffset.UTC).toString();

        final HttpUrl url = urlBase.newBuilder()
                .addQueryParameter("from", fromSearchParameter)
                .addQueryParameter("to", toSearchParameter)
                .build();

        final Request request = new Request.Builder()
                .url(url)
                .build();

        final Call call = httpClient.newCall(request);
        final Response response = call.execute();
        final ResponseBody responseBody = response.body();

        final List<ApiStudent> students = objectMapper.readValue(responseBody.string(), new TypeReference<>() {});

        assertEquals(2, students.size());
        assertTrue(students.stream().anyMatch(apiStudent -> apiStudent.getId().equals(1L)));
        assertTrue(students.stream().anyMatch(apiStudent -> apiStudent.getId().equals(3L)));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 4, 7})
    @DirtiesContext
    public void shouldDeleteById(final int idToDelete) throws IOException {

        Request getAllRequest = new Request.Builder()
                .url(urlBase)
                .build();

        Call getAllCall = httpClient.newCall(getAllRequest);
        Response getAllResponse = getAllCall.execute();
        ResponseBody getAllResponseBody = getAllResponse.body();

        List<ApiStudent> allStudents = objectMapper.readValue(getAllResponseBody.string(), new TypeReference<>() {});

        int studentsCount = allStudents.size();

        assertTrue(allStudents.stream().anyMatch(apiStudent -> apiStudent.getId().equals((long) idToDelete)));


        final HttpUrl url = urlBase.newBuilder()
                .addQueryParameter("id", String.valueOf(idToDelete))
                .build();

        final Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();

        final Call call = httpClient.newCall(request);
        final Response response = call.execute();
        final ResponseBody responseBody = response.body();

        final List<ApiStudent> students = objectMapper.readValue(responseBody.string(), new TypeReference<>() {});

        assertEquals(1, students.size());
        assertEquals(Long.valueOf(idToDelete), students.get(0).getId());


        getAllRequest = new Request.Builder()
                .url(urlBase)
                .build();

        getAllCall = httpClient.newCall(getAllRequest);
        getAllResponse = getAllCall.execute();
        getAllResponseBody = getAllResponse.body();

        allStudents = objectMapper.readValue(getAllResponseBody.string(), new TypeReference<>() {});

        assertEquals(studentsCount - 1, allStudents.size());
        assertTrue(allStudents.stream().noneMatch(apiStudent -> apiStudent.getId().equals((long) idToDelete)));
    }

}
