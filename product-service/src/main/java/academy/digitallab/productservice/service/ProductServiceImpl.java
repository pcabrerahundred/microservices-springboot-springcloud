package academy.digitallab.productservice.service;

import academy.digitallab.productservice.entity.Category;
import academy.digitallab.productservice.entity.Product;
import academy.digitallab.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product create(Product product) {
        product.setStatus("CREATED");
        product.setCreateAt(new Date());
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        Product productDB = getById(product.getId());
        if (productDB == null)
            return null;

        productDB.setName(product.getName());
        productDB.setDescription(product.getDescription());
        productDB.setPrice(product.getPrice());
        productDB.setCategory(product.getCategory());
        return productRepository.save(productDB);
    }

    @Override
    public Product delete(Long id) {
        Product productDB = getById(id);
        if (productDB == null)
            return null;

        productDB.setStatus("DELETED");
        return productRepository.save(productDB);
    }

    @Override
    public List<Product> getByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Product updateStock(Long id, Integer quantity) {
        Product productDB = getById(id);
        if (productDB == null)
            return null;

        Integer stock = productDB.getStock() + quantity;
        productDB.setStock(stock);
        return productRepository.save(productDB);
    }

}
