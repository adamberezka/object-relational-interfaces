package pl.polsl.ior.spring.student;

import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import pl.polsl.ior.spring.api.Api;
import pl.polsl.ior.spring.api.ApiCourse;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CourseApiTest extends ApiTestBase {

    private final HttpUrl urlBase = HttpUrl.parse("http://localhost:8080" + Api.Course.ENDPOINT);

    @Test
    public void shouldGetAll() throws IOException {
        final Request request = new Request.Builder()
                .url(urlBase)
                .build();

        final Call call = httpClient.newCall(request);
        final Response response = call.execute();
        final ResponseBody responseBody = response.body();

        final List<ApiCourse> courses = objectMapper.readValue(responseBody.string(), new TypeReference<>() {});

        assertEquals(9, courses.size());
    }

    @Test
    public void shouldFindByCertTypeAndStartDateBetween() throws IOException {
        final String certTypeSearchParameter = "Basic";
        final String dateBetweenFromSearchParameter =
                OffsetDateTime.of(2022, 10, 1, 0, 0, 0, 0, ZoneOffset.UTC).toString();
        final String dateBetweenToSearchParameter =
                OffsetDateTime.of(2022, 12, 31, 0, 0, 0, 0, ZoneOffset.UTC).toString();

        final HttpUrl url = urlBase.newBuilder()
                .addQueryParameter("certType", certTypeSearchParameter)
                .addQueryParameter("startDateFrom", dateBetweenFromSearchParameter)
                .addQueryParameter("startDateTo", dateBetweenToSearchParameter)
                .build();

        final Request request = new Request.Builder()
                .url(url)
                .build();

        final Call call = httpClient.newCall(request);
        final Response response = call.execute();
        final ResponseBody responseBody = response.body();

        final List<ApiCourse> courses = objectMapper.readValue(responseBody.string(), new TypeReference<>() {});

        assertEquals(2, courses.size());
        assertTrue(courses.stream().anyMatch(apiCourse -> apiCourse.getId().equals(1L)));
        assertTrue(courses.stream().anyMatch(apiCourse -> apiCourse.getId().equals(3L)));
    }

    @Test
    public void shouldFindByCertTypeAndStartDateBetweenPageable() throws IOException {
        final String certTypeSearchParameter = "Rookie";
        final String dateBetweenFromSearchParameter =
                OffsetDateTime.of(2022, 10, 1, 0, 0, 0, 0, ZoneOffset.UTC).toString();
        final String dateBetweenToSearchParameter =
                OffsetDateTime.of(2024, 12, 31, 0, 0, 0, 0, ZoneOffset.UTC).toString();
        final String pageSize = "2";
        final String firstPageNumber = "0";

        final HttpUrl firstPageUrl = urlBase.newBuilder()
                .addQueryParameter("certType", certTypeSearchParameter)
                .addQueryParameter("startDateFrom", dateBetweenFromSearchParameter)
                .addQueryParameter("startDateTo", dateBetweenToSearchParameter)
                .addQueryParameter("pageSize", pageSize)
                .addQueryParameter("pageNumber", firstPageNumber)
                .build();

        final Request firstPageRequest = new Request.Builder()
                .url(firstPageUrl)
                .build();

        final Call firstPageCall = httpClient.newCall(firstPageRequest);
        final Response firstPageResponse = firstPageCall.execute();
        final ResponseBody firstPageResponseBody = firstPageResponse.body();

        final List<ApiCourse> firstPageCourses = objectMapper.readValue(firstPageResponseBody.string(), new TypeReference<>() {});

        assertEquals(Integer.valueOf(pageSize), firstPageCourses.size());

        final String secondPageNumber = "1";

        final HttpUrl secondPageUrl = urlBase.newBuilder()
                .addQueryParameter("certType", certTypeSearchParameter)
                .addQueryParameter("startDateFrom", dateBetweenFromSearchParameter)
                .addQueryParameter("startDateTo", dateBetweenToSearchParameter)
                .addQueryParameter("pageSize", pageSize)
                .addQueryParameter("pageNumber", secondPageNumber)
                .build();

        final Request secondPageRequest = new Request.Builder()
                .url(secondPageUrl)
                .build();

        final Call secondPageCall = httpClient.newCall(secondPageRequest);
        final Response secondPageResponse = secondPageCall.execute();
        final ResponseBody secondPageResponseBody = secondPageResponse.body();

        final List<ApiCourse> secondPageCourses = objectMapper.readValue(secondPageResponseBody.string(), new TypeReference<>() {});

        assertEquals(Integer.valueOf(pageSize), secondPageCourses.size());

        assertTrue(secondPageCourses.stream().noneMatch(apiStudent ->
                apiStudent.getId().equals(firstPageCourses.get(0).getId())));
        assertTrue(secondPageCourses.stream().noneMatch(apiStudent ->
                apiStudent.getId().equals(firstPageCourses.get(1).getId())));
        assertTrue(firstPageCourses.stream().noneMatch(apiStudent ->
                apiStudent.getId().equals(secondPageCourses.get(0).getId())));
        assertTrue(firstPageCourses.stream().noneMatch(apiStudent ->
                apiStudent.getId().equals(secondPageCourses.get(1).getId())));
    }

    @Test
    public void shouldFindByCertTypeLike() throws IOException {
        final String certTypeLikeSearchParameter = "Professional%"; // TODO

        final HttpUrl url = urlBase.newBuilder()
                .addQueryParameter("certTypeLike", certTypeLikeSearchParameter)
                .build();

        final Request request = new Request.Builder()
                .url(url)
                .build();

        final Call call = httpClient.newCall(request);
        final Response response = call.execute();
        final ResponseBody responseBody = response.body();

        final List<ApiCourse> courses = objectMapper.readValue(responseBody.string(), new TypeReference<>() {});

        assertEquals(2, courses.size());
    }

    @Test
    public void shouldFindDistinctByCertType() throws IOException {
        final String certTypeSearchParameter = "Rookie"; // TODO

        final HttpUrl url = urlBase.newBuilder()
                .addQueryParameter("distinctByCertType", certTypeSearchParameter)
                .build();

        final Request request = new Request.Builder()
                .url(url)
                .build();

        final Call call = httpClient.newCall(request);
        final Response response = call.execute();
        final ResponseBody responseBody = response.body();

        final List<ApiCourse> courses = objectMapper.readValue(responseBody.string(), new TypeReference<>() {});

        assertEquals(4, courses.size());
    }

    @Test
    public void shouldFindByCertTypeOrStartDateBetween() throws IOException {
        final String certTypeSearchParameter = "Professional";
        final String dateBetweenFromSearchParameter =
                OffsetDateTime.of(2022, 10, 1, 0, 0, 0, 0, ZoneOffset.UTC).toString();
        final String dateBetweenToSearchParameter =
                OffsetDateTime.of(2022, 12, 31, 0, 0, 0, 0, ZoneOffset.UTC).toString();

        final HttpUrl url = urlBase.newBuilder()
                .addQueryParameter("orCertType", certTypeSearchParameter)
                .addQueryParameter("orStartDateBetweenFrom", dateBetweenFromSearchParameter)
                .addQueryParameter("orStartDateBetweenTo", dateBetweenToSearchParameter)
                .build();

        final Request request = new Request.Builder()
                .url(url)
                .build();

        final Call call = httpClient.newCall(request);
        final Response response = call.execute();
        final ResponseBody responseBody = response.body();

        final List<ApiCourse> courses = objectMapper.readValue(responseBody.string(), new TypeReference<>() {});

        assertEquals(3, courses.size());
    }

    @Test
    public void shouldFindByDescriptionContainingIgnoreCase() throws IOException {
        final String descriptionContainsSearchParameter = "level";

        final HttpUrl url = urlBase.newBuilder()
                .addQueryParameter("descriptionContains", descriptionContainsSearchParameter)
                .build();

        final Request request = new Request.Builder()
                .url(url)
                .build();

        final Call call = httpClient.newCall(request);
        final Response response = call.execute();
        final ResponseBody responseBody = response.body();

        final List<ApiCourse> courses = objectMapper.readValue(responseBody.string(), new TypeReference<>() {});

        assertEquals(3, courses.size());
    }

    @Test
    public void shouldFindWithMoreThanNStudents() throws IOException {
        final String moreStudentsThanSearchParameter = "1"; // TODO

        final HttpUrl url = urlBase.newBuilder()
                .addQueryParameter("withMoreThanStudents", moreStudentsThanSearchParameter)
                .build();

        final Request request = new Request.Builder()
                .url(url)
                .build();

        final Call call = httpClient.newCall(request);
        final Response response = call.execute();
        final ResponseBody responseBody = response.body();

        final List<ApiCourse> courses = objectMapper.readValue(responseBody.string(), new TypeReference<>() {});

        assertEquals(2, courses.size());
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

        List<ApiCourse> allCourses = objectMapper.readValue(getAllResponseBody.string(), new TypeReference<>() {});

        int coursesCount = allCourses.size();

        assertTrue(allCourses.stream().anyMatch(apiCourse -> apiCourse.getId().equals((long) idToDelete)));


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

        final List<ApiCourse> courses = objectMapper.readValue(responseBody.string(), new TypeReference<>() {
        });

        assertEquals(1, courses.size());
        assertEquals(Long.valueOf(idToDelete), courses.get(0).getId());


        getAllRequest = new Request.Builder()
                .url(urlBase)
                .build();

        getAllCall = httpClient.newCall(getAllRequest);
        getAllResponse = getAllCall.execute();
        getAllResponseBody = getAllResponse.body();

        allCourses = objectMapper.readValue(getAllResponseBody.string(), new TypeReference<>() {
        });

        assertEquals(coursesCount - 1, allCourses.size());
        assertTrue(allCourses.stream().noneMatch(apiCourse -> apiCourse.getId().equals((long) idToDelete)));
    }
}
