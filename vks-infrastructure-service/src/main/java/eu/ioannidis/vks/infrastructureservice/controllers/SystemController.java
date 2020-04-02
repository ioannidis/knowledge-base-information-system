package eu.ioannidis.vks.infrastructureservice.controllers;

import eu.ioannidis.vks.infrastructureservice.models.entities.SystemEntity;
import eu.ioannidis.vks.infrastructureservice.services.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(SystemController.BASE_URL)
public class SystemController {

    static final String BASE_URL = "/v1/systems";

    private SystemService systemService;

    @Autowired
    public SystemController(SystemService systemService) {
        this.systemService = systemService;
    }

    @GetMapping()
    public Collection<SystemEntity> getSystems() {
        return systemService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SystemEntity> getSystem(@PathVariable String id) {
        return systemService.findById(UUID.fromString(id))
                .map(x -> new ResponseEntity<SystemEntity>(x, HttpStatus.OK))
                .orElse(new ResponseEntity<SystemEntity>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public SystemEntity createSystem(@Valid @RequestBody SystemEntity systemEntity) {
        return systemService.save(systemEntity);
    }

    @PutMapping
    public ResponseEntity updateSystem(@Valid @RequestBody SystemEntity systemEntity) {
        return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("/{id}")
    public void deleteSystem(@PathVariable String id) {
        systemService.deleteById(UUID.fromString(id));
    }

    public boolean existsById(String id) {
        return systemService.existsById(UUID.fromString(id));
    }

    @GetMapping("/count")
    public Map<String, Long> count() {
        Map<String, Long> count = new HashMap<>();
        count.put("count", systemService.count());
        return count;
    }

}
