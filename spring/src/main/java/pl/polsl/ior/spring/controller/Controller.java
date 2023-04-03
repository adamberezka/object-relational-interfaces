package pl.polsl.ior.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.ior.spring.repository.TestRepository;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final TestRepository testRepository;

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/echo")
    public ResponseEntity<String> echo(@RequestBody EchoRequest request) {

        return ResponseEntity.ok(request.getValue());
    }
}
