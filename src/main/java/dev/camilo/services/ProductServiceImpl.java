package dev.camilo.services;

import dev.camilo.entities.Product;
import dev.camilo.repositories.ProductRepository;
import jakarta.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductServiceImpl implements ProductService {
  @Inject
  ProductRepository productRepository;

  //REST methods
  @Override
  public List<Product> list() {
    return productRepository.findAll();
  }

  @Override
  public ResponseEntity<Product> add(Product product){
    productRepository.save(product);
    return ResponseEntity.ok(product);
  }

  @Override
  public ResponseEntity<?> delete(Long id){
    Optional<Product> productToDelete = productRepository.findById(id);
    if(productToDelete.isPresent()) {
      productRepository.deleteById(productToDelete.get().getId());
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

  @Override
  public ResponseEntity<Product> updateProduct(Long id, Product product){
    Optional<Product> productToUpdate = productRepository.findById(id);
    if(productToUpdate.isPresent()) {
      Product productToSet = productToUpdate.get();
      productToSet.setName(product.getName());
      productToSet.setDescription(product.getDescription());

      Product productSaved = productRepository.save(productToSet);
      return ResponseEntity.ok(productSaved);
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

  @Override
  public ResponseEntity<Product> getProductById(Long id){
    Optional<Product> product = productRepository.findById(id);
    if(product.isPresent()) return ResponseEntity.ok(product.get());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

  //GraphQL Methods
  @Override
  public Product addMutation(Product product) {
    return productRepository.save(product);
  }

  @Override
  public boolean deleteMutation(Long id) {
    productRepository.deleteById(id);
    return productRepository.existsById(id);
  }

  @Override
  public Product updateProductMutation(Long id, Product product) {
    Optional<Product> productToUpdate = productRepository.findById(id);
    if(productToUpdate.isPresent()) {
      Product productToSet = productToUpdate.get();
      productToSet.setName(product.getName());
      productToSet.setDescription(product.getDescription());
      return productRepository.save(productToSet);
    }
    return null;
  }

  @Override
  public Product getProductByIdQuery(Long id) {
    Optional<Product> product = productRepository.findById(id);
    return product.orElse(null);
  }
}
