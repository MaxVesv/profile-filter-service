package by.javaguru.api.controller;

import by.javaguru.usecasses.FilterService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@Tag(name = "Profile filter Controller", description = "API for testing with HTTP")
@RestController
@RequestMapping("/api/v1/profile-filter/")
@RequiredArgsConstructor
public class ApiProfileFilterController {

    private final FilterService filterService;

    @GetMapping
    public ResponseEntity<?> getTestHello() {
        return new ResponseEntity<>("Hello from profile filter service", HttpStatus.OK);
    }

    @GetMapping(("all-enabled-services"))
    public ResponseEntity<?> getAllListServicesForFilter() {
        Set<String> listServices = filterService.getListEnabledFilters();
        return new ResponseEntity<>("List of services: " + listServices.toString(), HttpStatus.OK);
    }


}
