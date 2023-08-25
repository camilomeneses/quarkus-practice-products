package dev.camilo.repositories;

import dev.camilo.entities.Product;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ProductRepository {

  @Inject
  EntityManager em;

  @Transactional
  public void createProduct(Product product) {
    em.persist(product);
  }

  @Transactional
  public void deleteProduct(Long id) {
    Product product = getProductById(id);
    em.remove(em.contains(product) ? product : em.merge(product));
  }

  @Transactional
  public List<Product> getProducts() {
    return em.createQuery("select p from Product p").getResultList();
  }

  @Transactional
  public Product getProductById(Long id) {
    return em.find(Product.class, id);
  }

  @Transactional
  public Product updateProduct(Long id, Product product) {
    Product existingProduct = getProductById(id);
    if (existingProduct == null) {
      throw new RuntimeException("Product with ID " + id + " not found");
    }
    existingProduct.setName(product.getName());
    existingProduct.setDescription(product.getDescription());
    return em.merge(existingProduct);
  }
}
