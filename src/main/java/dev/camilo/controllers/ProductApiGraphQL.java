package dev.camilo.controllers;


import dev.camilo.entities.Product;
import dev.camilo.services.ProductService;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;

import java.util.List;

@GraphQLApi
public class ProductApiGraphQL {

  @Inject
  ProductService productService;

  @Query("allProducts")
  @Description("Obtener todos los productos desde la base de datos")
  public List<Product> getAllProducts(){
    return productService.list();
  }

  @Query("getProduct")
  @Description("Obtener producto por id desde la base de datos")
  public Product getProductByIdQuery(@Named("id") Long id){
    return productService.getProductByIdQuery(id);
  }
}
