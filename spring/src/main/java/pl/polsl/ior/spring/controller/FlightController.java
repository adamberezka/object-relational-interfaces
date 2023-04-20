package pl.polsl.ior.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.ior.spring.api.Api;
import pl.polsl.ior.spring.api.ApiFlight;
import pl.polsl.ior.spring.api.conversion.FlightApiConversion;
import pl.polsl.ior.spring.domain.Flight;
import pl.polsl.ior.spring.service.FlightService;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

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

    @GetMapping(params = {"hours", "from", "to"})
    public ResponseEntity<List<ApiFlight>> findByHoursAndDateBetween(@RequestParam Integer hours,
                                                                     @RequestParam OffsetDateTime from,
                                                                     @RequestParam OffsetDateTime to) {
        return ResponseEntity.ok(
                flightService.findByHoursAndDateBetween(hours, from, to)
                        .stream()
                        .map(FlightApiConversion::toApi)
                        .toList()
        );
    }

    @GetMapping(params = {"hours", "from", "to", "pageSize", "pageNumber"})
    public ResponseEntity<List<ApiFlight>> findByHoursAndDateBetween(@RequestParam Integer hours,
                                                                     @RequestParam OffsetDateTime from,
                                                                     @RequestParam OffsetDateTime to,
                                                                     @RequestParam Integer pageSize,
                                                                     @RequestParam Integer pageNumber) {
        return ResponseEntity.ok(
                flightService.findByHoursAndDateBetween(hours, from, to, pageSize, pageNumber)
                        .stream()
                        .map(FlightApiConversion::toApi)
                        .toList()
        );
    }

    @GetMapping(params = "descriptionLike")
    public ResponseEntity<List<ApiFlight>> findByDescriptionLike(@RequestParam String descriptionLike) {
        return ResponseEntity.ok(
                flightService.findByDescriptionLike(descriptionLike)
                        .stream()
                        .map(FlightApiConversion::toApi)
                        .toList()
        );
    }

    @GetMapping(params = "distinctByHours")
    public ResponseEntity<List<ApiFlight>> findDistinctByHours(@RequestParam Integer distinctByHours) {
        return ResponseEntity.ok(
                flightService.findDistinctByHours(distinctByHours)
                        .stream()
                        .map(FlightApiConversion::toApi)
                        .toList()
        );
    }

    @GetMapping(params = {"orHours", "orDateBetweenFrom", "orDateBetweenTo"})
    public ResponseEntity<List<ApiFlight>> findByHoursOrDateBetween(@RequestParam Integer orHours,
                                                                    @RequestParam OffsetDateTime orDateBetweenFrom,
                                                                    @RequestParam OffsetDateTime orDateBetweenTo) {
        return ResponseEntity.ok(
                flightService.findByHoursOrDateBetween(orHours, orDateBetweenFrom, orDateBetweenTo)
                        .stream()
                        .map(FlightApiConversion::toApi)
                        .toList()
        );
    }

    @GetMapping(params = "descriptionContains")
    public ResponseEntity<List<ApiFlight>> findWithDescriptionContainingIgnoreCase(@RequestParam String descriptionContains) {
        return ResponseEntity.ok(
                flightService.findWithDescriptionContainingIgnoreCase(descriptionContains)
                        .stream()
                        .map(FlightApiConversion::toApi)
                        .toList()
        );
    }

    @GetMapping(params = "longerThan")
    public ResponseEntity<List<ApiFlight>> findLongerThan(@RequestParam(name = "longerThan") int hours) {
        return ResponseEntity.ok(
                flightService.findLongerThan(hours)
                        .stream()
                        .map(FlightApiConversion::toApi)
                        .toList()
        );
    }

    @DeleteMapping
    public ResponseEntity<List<ApiFlight>> deleteById(@RequestParam Long id) {
        return ResponseEntity.ok(
                flightService.deleteById(id)
                        .stream()
                        .map(FlightApiConversion::toApi)
                        .toList()
        );
    }

}
