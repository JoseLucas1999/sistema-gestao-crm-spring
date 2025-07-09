package br.com.lucas.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucas.model.Product;
import br.com.lucas.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // ----------------------------------------------
    // GET ALL
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    // ----------------------------------------------
    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ----------------------------------------------
    // CREATE
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.save(product));
    }

    // ----------------------------------------------
    // CREATE ARRAY JSON
    @PostMapping("/batch")
    public ResponseEntity<List<Product>> createMultipleProducts(@RequestBody List<Product> products) {
        return ResponseEntity.ok(productService.saveAll(products));
    }

    // ----------------------------------------------
    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updated) {
        return productService.findById(id).map(product -> {
            product.setName(updated.getName());
            product.setDescription(updated.getDescription());
            product.setPrice(updated.getPrice());
            product.setCategory(updated.getCategory());
            product.setAvaliable(updated.isAvaliable());
            return ResponseEntity.ok(productService.save(product));
        }).orElse(ResponseEntity.notFound().build());
    }

    // ----------------------------------------------
    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ----------------------------------------------
    // FIND BY AVAILABILITY
    @GetMapping("/available")
    public ResponseEntity<List<Product>> getAvailableProducts() {
        return ResponseEntity.ok(productService.findByAvaliable(true));
    }

    @GetMapping("/not-available")
    public ResponseEntity<List<Product>> getUnavailableProducts() {
        return ResponseEntity.ok(productService.findByAvaliable(false));
    }

    // ----------------------------------------------
    // SEARCH BY CATEGORY
    @GetMapping("/category")
    public ResponseEntity<List<Product>> getByCategory(@RequestParam String category) {
        return ResponseEntity.ok(productService.findByCategoryIgnoreCase(category));
    }

    // ----------------------------------------------
    // SEARCH BY NAME (contains)
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(productService.findByNameContaining(name));
    }
}
