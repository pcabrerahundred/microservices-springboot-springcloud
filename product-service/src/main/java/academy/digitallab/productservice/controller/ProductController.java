package academy.digitallab.productservice.controller;

import academy.digitallab.productservice.entity.Category;
import academy.digitallab.productservice.entity.Product;
import academy.digitallab.productservice.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> listProducts(@RequestParam(name = "categoryId", required = false) Long categoryId) {
        List<Product> products = new ArrayList<>();
        if (categoryId == null) {
            products = productService.getAll();
            if (products.isEmpty())
                return ResponseEntity.noContent().build();
        } else {
            products = productService.getByCategory(Category.builder().id(categoryId).build());
            if (products.isEmpty())
                return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
        Product product = productService.getById(id);
        if (product == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }

        Product productDB = productService.create(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(productDB);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        product.setId(id);
        Product productDB = productService.update(product);
        if (productDB == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(productDB);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id) {
        Product productDB = productService.delete(id);
        if (productDB == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(productDB);
    }

    @GetMapping("/{id}/stock")
    public ResponseEntity<Product> updateStockProduct(@PathVariable("id") Long id,
                                               @RequestParam(name = "quantity", required = true) Integer quantity) {
        Product productDB = productService.updateStock(id, quantity);
        if (productDB == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(productDB);
    }

    private String formatMessage(BindingResult result) {
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(err -> {
                    Map<String, String> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());

        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return jsonString;
    }

}
