package dev.camilo.controllers;

import dev.camilo.entities.Product;
import dev.camilo.repositories.ProductRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(
    path = "/products",
    consumes = MediaType.APPLICATION_JSON,
    produces = MediaType.APPLICATION_JSON
)
public class ProductApi {

  @Inject
  ProductRepository productRepository;

  @GetMapping
  public List<Product> list() {
    return productRepository.findAll();
  }

  @PostMapping
  public ResponseEntity<Product> add(Product product){
    productRepository.save(product);
    return ResponseEntity.ok(product);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable String id){
    Optional<Product> productToDelete = productRepository.findById(Long.parseLong(id));
    if(productToDelete.isPresent()) {
      productRepository.deleteById(productToDelete.get().getId());
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProduct(@PathVariable String id, Product product){
    Optional<Product> productToUpdate = productRepository.findById(Long.parseLong(id));
    if(productToUpdate.isPresent()) {
      Product productToSet = productToUpdate.get();
      productToSet.setName(product.getName());
      productToSet.setDescription(product.getDescription());

      Product productSaved = productRepository.save(productToSet);
      return ResponseEntity.ok(productSaved);
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable String id){
    Optional<Product> product = productRepository.findById(Long.parseLong(id));
    if(product.isPresent()) return ResponseEntity.ok(product.get());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }
}
