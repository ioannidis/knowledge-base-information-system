package eu.ioannidis.vks.infrastructureservice.controllers;

import eu.ioannidis.vks.infrastructureservice.models.entities.ProductEntity;
import eu.ioannidis.vks.infrastructureservice.models.responses.ProductIdTitleResponse;
import eu.ioannidis.vks.infrastructureservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(ProductController.BASE_URL)
public class ProductController {

    final static String BASE_URL = "/v1/products";

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Collection<ProductEntity> getProducts() {
        return productService.findAll();
    }

//    @GetMapping
//    public List<ProductIdTitleResponse> getProducts(@RequestParam String keyItem) {
//        return productService.findAllProjection(ProductIdTitleResponse.class);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> getProduct(@PathVariable String id) {
        return productService.findById(UUID.fromString(id))
                .map(x -> new ResponseEntity<ProductEntity>(x, HttpStatus.OK))
                .orElse(new ResponseEntity<ProductEntity>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public ProductEntity createProduct(@Valid @RequestBody ProductEntity productEntity) {
        return productService.save(productEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable String id, @Valid @RequestBody ProductEntity productEntity) {
        return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        productService.deleteById((UUID.fromString(id)));
    }

    public boolean existsById(String id) {
        return productService.existsById(UUID.fromString(id));
    }

    @GetMapping("/count")
    public Map<String, Long> count() {
        Map<String, Long> count = new HashMap<>();
        count.put("count", productService.count());
        return count;
    }
}
