package pl.polsl.ior.spring.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import okhttp3.OkHttpClient;

public abstract class ApiTestBase {
    protected final ObjectMapper objectMapper = JsonMapper.builder()
            .findAndAddModules()
            .build();
    protected final OkHttpClient httpClient = new OkHttpClient();
}
