package pl.polsl.ior.spring.student;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import pl.polsl.ior.spring.api.Api;
import pl.polsl.ior.spring.api.ApiCourse;
import pl.polsl.ior.spring.api.ApiFlight;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class FlightApiTest extends ApiTestBase {

    private final HttpUrl urlBase = HttpUrl.parse("http://localhost:8080" + Api.Flight.ENDPOINT);

    @Test
    public void shouldGetAll() throws IOException {
        final Request request = new Request.Builder()
                .url(urlBase)
                .build();

        final Call call = httpClient.newCall(request);
        final Response response = call.execute();
        final ResponseBody responseBody = response.body();

        final List<ApiFlight> flights = objectMapper.readValue(responseBody.string(), new TypeReference<>() {});

        assertEquals(7, flights.size());
    }

    @Test
    public void shouldFindByHoursAndDateBetween() throws IOException {
        final String hoursSearchParameter = "2";
        final String dateBetweenFromSearchParameter =
                OffsetDateTime.of(2022, 10, 11, 0, 0, 0, 0, ZoneOffset.UTC).toString();
        final String dateBetweenToSearchParameter =
                OffsetDateTime.of(2022, 12, 31, 0, 0, 0, 0, ZoneOffset.UTC).toString();

        final HttpUrl url = urlBase.newBuilder()
                .addQueryParameter("hours", hoursSearchParameter)
                .addQueryParameter("from", dateBetweenFromSearchParameter)
                .addQueryParameter("to", dateBetweenToSearchParameter)
                .build();

        final Request request = new Request.Builder()
                .url(url)
                .build();

        final Call call = httpClient.newCall(request);
        final Response response = call.execute();
        final ResponseBody responseBody = response.body();

        final List<ApiFlight> flights = objectMapper.readValue(responseBody.string(), new TypeReference<>() {});

        assertEquals(2, flights.size());
    }

    @Test
    public void shouldFindByHoursAndDateBetweenPageable() throws IOException {
        final String hoursSearchParameter = "2";
        final String dateBetweenFromSearchParameter =
                OffsetDateTime.of(2022, 10, 1, 0, 0, 0, 0, ZoneOffset.UTC).toString();
        final String dateBetweenToSearchParameter =
                OffsetDateTime.of(2022, 12, 31, 0, 0, 0, 0, ZoneOffset.UTC).toString();
        final String pageSize = "1";
        String firstPageNumber = "0";

        final HttpUrl firstPageUrl = urlBase.newBuilder()
                .addQueryParameter("hours", hoursSearchParameter)
                .addQueryParameter("from", dateBetweenFromSearchParameter)
                .addQueryParameter("to", dateBetweenToSearchParameter)
                .addQueryParameter("pageSize", pageSize)
                .addQueryParameter("pageNumber", firstPageNumber)
                .build();

        final Request firstPageRequest = new Request.Builder()
                .url(firstPageUrl)
                .build();

        final Call firstPageRequestCall = httpClient.newCall(firstPageRequest);
        final Response firstPageRequestResponse = firstPageRequestCall.execute();
        final ResponseBody firstPageRequestResponseBody = firstPageRequestResponse.body();

        final List<ApiFlight> firstPageFlights = objectMapper.readValue(firstPageRequestResponseBody.string(), new TypeReference<>() {});

        assertEquals(Integer.valueOf(pageSize), firstPageFlights.size());

        String secondPageNumber = "1";

        final HttpUrl secondPageUrl = urlBase.newBuilder()
                .addQueryParameter("hours", hoursSearchParameter)
                .addQueryParameter("from", dateBetweenFromSearchParameter)
                .addQueryParameter("to", dateBetweenToSearchParameter)
                .addQueryParameter("pageSize", pageSize)
                .addQueryParameter("pageNumber", secondPageNumber)
                .build();

        final Request secondPageRequest = new Request.Builder()
                .url(secondPageUrl)
                .build();

        final Call secondPageCall = httpClient.newCall(secondPageRequest);
        final Response secondPageResponse = secondPageCall.execute();
        final ResponseBody secondPageResponseBody = secondPageResponse.body();

        final List<ApiFlight> secondPageFlights = objectMapper.readValue(secondPageResponseBody.string(), new TypeReference<>() {});

        assertEquals(Integer.valueOf(pageSize), secondPageFlights.size());

        assertTrue(secondPageFlights.stream().noneMatch(apiStudent ->
                apiStudent.getId().equals(firstPageFlights.get(0).getId())));
        assertTrue(firstPageFlights.stream().noneMatch(apiStudent ->
                apiStudent.getId().equals(secondPageFlights.get(0).getId())));
    }

    @Test
    public void shouldFindByDescriptionLike() throws IOException {
        final String descriptionLikeSearchParameter = "Fli%";

        final HttpUrl url = urlBase.newBuilder()
                .addQueryParameter("descriptionLike", descriptionLikeSearchParameter)
                .build();

        final Request request = new Request.Builder()
                .url(url)
                .build();

        final Call call = httpClient.newCall(request);
        final Response response = call.execute();
        final ResponseBody responseBody = response.body();

        final List<ApiFlight> flights = objectMapper.readValue(responseBody.string(), new TypeReference<>() {});

        assertEquals(3, flights.size());
    }

    @Test
    public void shouldFindDistinctByHours() throws IOException {
        final String hoursSearchParameter = "2";

        final HttpUrl url = urlBase.newBuilder()
                .addQueryParameter("distinctByHours", hoursSearchParameter)
                .build();

        final Request request = new Request.Builder()
                .url(url)
                .build();

        final Call call = httpClient.newCall(request);
        final Response response = call.execute();
        final ResponseBody responseBody = response.body();

        final List<ApiFlight> flights = objectMapper.readValue(responseBody.string(), new TypeReference<>() {});

        assertEquals(3, flights.size());
    }

    @Test
    public void shouldFindByHoursOrDateBetween() throws IOException {
        final String hoursSearchParameter = "2";
        final String dateBetweenFromSearchParameter =
                OffsetDateTime.of(2022, 10, 1, 0, 0, 0, 0, ZoneOffset.UTC).toString();
        final String dateBetweenToSearchParameter =
                OffsetDateTime.of(2022, 12, 31, 0, 0, 0, 0, ZoneOffset.UTC).toString();


        final HttpUrl url = urlBase.newBuilder()
                .addQueryParameter("orHours", hoursSearchParameter)
                .addQueryParameter("orDateBetweenFrom", dateBetweenFromSearchParameter)
                .addQueryParameter("orDateBetweenTo", dateBetweenToSearchParameter)
                .build();

        final Request request = new Request.Builder()
                .url(url)
                .build();

        final Call call = httpClient.newCall(request);
        final Response response = call.execute();
        final ResponseBody responseBody = response.body();

        final List<ApiFlight> flights = objectMapper.readValue(responseBody.string(), new TypeReference<>() {});

        assertEquals(6, flights.size());
    }

    @Test
    public void shouldFindWithDescriptionContainingIgnoreCase() throws IOException {
        final String descriptionContainsSearchParameter = "barrel";

        final HttpUrl url = urlBase.newBuilder()
                .addQueryParameter("descriptionContains", descriptionContainsSearchParameter)
                .build();

        final Request request = new Request.Builder()
                .url(url)
                .build();

        final Call call = httpClient.newCall(request);
        final Response response = call.execute();
        final ResponseBody responseBody = response.body();

        final List<ApiFlight> flights = objectMapper.readValue(responseBody.string(), new TypeReference<>() {});

        assertEquals(3, flights.size());
    }

    @Test
    public void shouldFindLongerThan() throws IOException {
        final String longerThanSearchParameters = "2";

        final HttpUrl url = urlBase.newBuilder()
                .addQueryParameter("longerThan", longerThanSearchParameters)
                .build();

        final Request request = new Request.Builder()
                .url(url)
                .build();

        final Call call = httpClient.newCall(request);
        final Response response = call.execute();
        final ResponseBody responseBody = response.body();

        final List<ApiFlight> flights = objectMapper.readValue(responseBody.string(), new TypeReference<>() {});

        assertEquals(3, flights.size());
        assertTrue(flights.stream().allMatch(apiFlight -> apiFlight.getHours() > Integer.parseInt(longerThanSearchParameters)));
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

        List<ApiFlight> allFlights = objectMapper.readValue(getAllResponseBody.string(), new TypeReference<>() {
        });

        int flightsCount = allFlights.size();

        assertTrue(allFlights.stream().anyMatch(apiFlight -> apiFlight.getId().equals((long) idToDelete)));


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

        final List<ApiFlight> flights = objectMapper.readValue(responseBody.string(), new TypeReference<>() {
        });

        assertEquals(1, flights.size());
        assertEquals(Long.valueOf(idToDelete), flights.get(0).getId());


        getAllRequest = new Request.Builder()
                .url(urlBase)
                .build();

        getAllCall = httpClient.newCall(getAllRequest);
        getAllResponse = getAllCall.execute();
        getAllResponseBody = getAllResponse.body();

        allFlights = objectMapper.readValue(getAllResponseBody.string(), new TypeReference<>() {
        });

        assertEquals(flightsCount - 1, allFlights.size());
        assertTrue(allFlights.stream().noneMatch(apiFlight -> apiFlight.getId().equals((long) idToDelete)));
    }

}
