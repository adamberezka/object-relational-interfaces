package pl.polsl.ior.spring;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.polsl.ior.spring.persistance.DataLoader;

@SpringBootApplication
@RequiredArgsConstructor
public class Application implements ApplicationRunner {

    private final DataLoader dataLoader;

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(final ApplicationArguments args) throws Exception {
        dataLoader.loadInitialData();
    }
}
