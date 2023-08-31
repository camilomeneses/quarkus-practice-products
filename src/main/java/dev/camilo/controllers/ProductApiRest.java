package dev.camilo.controllers;

import dev.camilo.entities.Product;
import dev.camilo.services.ProductService;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
    path = "/products",
    consumes = MediaType.APPLICATION_JSON,
    produces = MediaType.APPLICATION_JSON
)
public class ProductApiRest {

  @Inject
  ProductService productService;

  @GetMapping
  public List<Product> list() {
    return productService.list();
  }

  @PostMapping
  public ResponseEntity<Product> add(Product product) {
    return productService.add(product);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    return productService.delete(id);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProduct(@PathVariable Long id, Product product) {
    return productService.updateProduct(id, product);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable Long id) {
    return productService.getProductById(id);
  }
}
