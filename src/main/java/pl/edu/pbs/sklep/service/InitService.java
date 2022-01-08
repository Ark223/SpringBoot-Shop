package pl.edu.pbs.sklep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pbs.sklep.model.Product;
import pl.edu.pbs.sklep.model.User;
import pl.edu.pbs.sklep.repository.ProductRepository;
import pl.edu.pbs.sklep.repository.UserRepository;
import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Service
public class InitService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @PostConstruct
    public void init() {
        User u1 = new User(0, "marek123", "polska55", "maras123@wp.pl", "439898952", "Marek", "Nowak",
            LocalDateTime.of(2021, 10, 31, 12, 0, 0), LocalDateTime.of(2021, 10, 31, 12, 0, 0));
        userRepository.save(u1);

        Product p1 = new Product(0, "GeForce RTX 3090", "Karty graficzne",
            "Przepotężna karta graficzna o wydajności klasy TITAN",
            "https://www.nvidia.com/content/dam/en-zz/Solutions/geforce/ampere" +
            "/rtx-3090/geforce-rtx-3090-shop-630-d@2x.png", 7029.0f, (byte)1);
        productRepository.save(p1);
    }
}
