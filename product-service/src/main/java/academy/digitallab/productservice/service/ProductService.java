package academy.digitallab.productservice.service;

import academy.digitallab.productservice.entity.Category;
import academy.digitallab.productservice.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAll();
    Product getById(Long id);
    Product create(Product product);
    Product update(Product product);
    Product delete(Long id);
    List<Product> getByCategory(Category category);
    Product updateStock(Long id, Integer quantity);

}
