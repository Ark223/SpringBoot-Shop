package pl.edu.pbs.sklep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pbs.sklep.model.Product;
import pl.edu.pbs.sklep.repository.ProductRepository;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public List<Product> getAllUsers() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProduct(@PathVariable Integer id) {
        return productRepository.findById(id);
    }
}
