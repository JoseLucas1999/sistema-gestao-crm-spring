package br.com.lucas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.lucas.model.Product;
import br.com.lucas.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    public Product save(Product product) {
        return repository.save(product);
    }

    public List<Product> saveAll(List<Product> products) {
        return repository.saveAll(products);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Product> findByAvaliable(boolean avaliable) {
        return repository.findByAvaliable(avaliable);
    }

    public List<Product> findByCategoryIgnoreCase(String category) {
        return repository.findByCategoryIgnoreCase(category);
    }

    public List<Product> findByNameContaining(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }
}
