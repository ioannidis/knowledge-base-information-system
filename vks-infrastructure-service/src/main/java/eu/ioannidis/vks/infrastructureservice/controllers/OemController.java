package eu.ioannidis.vks.infrastructureservice.controllers;

import eu.ioannidis.vks.infrastructureservice.models.entities.OemEntity;
import eu.ioannidis.vks.infrastructureservice.services.OemService;
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
@RequestMapping(OemController.BASE_URL)
public class OemController {

    static final String BASE_URL = "/v1/oems";

    private OemService oemService;

    @Autowired
    public OemController(OemService oemService) {
        this.oemService = oemService;
    }

    @GetMapping()
    public Collection<OemEntity> getSystems() {
        return oemService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OemEntity> getSystem(@PathVariable String id) {
        return oemService.findById(UUID.fromString(id))
                .map(x -> new ResponseEntity<OemEntity>(x, HttpStatus.OK))
                .orElse(new ResponseEntity<OemEntity>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public OemEntity createSystem(@Valid @RequestBody OemEntity systemEntity) {
        return oemService.save(systemEntity);
    }

    @PutMapping
    public ResponseEntity updateSystem(@Valid @RequestBody OemEntity systemEntity) {
        return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("/{id}")
    public void deleteSystem(@PathVariable String id) {
        oemService.deleteById(UUID.fromString(id));
    }

    public boolean existsById(String id) {
        return oemService.existsById(UUID.fromString(id));
    }

    @GetMapping("/count")
    public Map<String, Long> count() {
        Map<String, Long> count = new HashMap<>();
        count.put("count", oemService.count());
        return count;
    }
}
