package pl.polsl.ior.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.ior.spring.api.Api;
import pl.polsl.ior.spring.api.ApiFlight;
import pl.polsl.ior.spring.api.conversion.FlightApiConversion;
import pl.polsl.ior.spring.service.FlightService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(Api.Flight.ENDPOINT)
public class FlightController {

    private final FlightService flightService;

    @GetMapping
    public ResponseEntity<List<ApiFlight>> getAll() {
        return ResponseEntity.ok(
                flightService.getAll()
                        .stream()
                        .map(FlightApiConversion::toApi)
                        .toList()
        );
    }
}
