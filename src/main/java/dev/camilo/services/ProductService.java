package dev.camilo.services;

import dev.camilo.entities.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

  //REST Methods
  List<Product> list();
  ResponseEntity<Product> add(Product product);
  ResponseEntity<?> delete(Long id);
  ResponseEntity<Product> updateProduct(Long id, Product product);
  ResponseEntity<Product> getProductById(Long id);

  //GraphQL Methods
  Product addMutation(Product product);
  boolean deleteMutation(Long id);
  Product updateProductMutation(Long id, Product product);
  Product getProductByIdQuery(Long id);
}
