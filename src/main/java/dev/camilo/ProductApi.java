package dev.camilo;

import dev.camilo.entities.Product;
import dev.camilo.repositories.ProductRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/product")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductApi {

  @Inject
  ProductRepository productRepository;

  @GET
  public List<Product> list() {
    return productRepository.getProducts();
  }

  @POST
  public Response add(Product product){
    productRepository.createProduct(product);
    return Response.ok().build();
  }

  @DELETE
  @Path("/{id}")
  public Response delete(@PathParam("id") String id){
    productRepository.deleteProduct(Long.parseLong(id));
    return Response.noContent().build();
  }

  @PUT
  @Path("/{id}")
  public Response updateProduct(@PathParam("id") String id, Product product){
    Product persistedProduct = productRepository.updateProduct(Long.parseLong(id), product);
    return Response.ok(persistedProduct).build();
  }

  @GET
  @Path("/{id}")
  public Response getProductById(@PathParam("id") String id){
    Product product = productRepository.getProductById(Long.parseLong(id));
    return Response.ok(product).build();
  }
}
