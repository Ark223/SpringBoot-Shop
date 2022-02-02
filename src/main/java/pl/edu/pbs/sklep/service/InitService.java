package pl.edu.pbs.sklep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pbs.sklep.model.Product;
import pl.edu.pbs.sklep.model.User;
import pl.edu.pbs.sklep.repository.ProductRepository;
import pl.edu.pbs.sklep.repository.UserRepository;
import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Arrays;

@Service
public class InitService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @PostConstruct
    public void init() {
        User u1 = new User(0, "marek123", "polska55", "maras123@wp.pl",
            "ul. Rycerska 8, Bydgoszcz", "439898952", "Marek", "Nowak",
            LocalDateTime.of(2021, 10, 31, 12, 0, 0),
            LocalDateTime.of(2021, 10, 31, 12, 0, 0));
        userRepository.save(u1);

        Product p1 = new Product(0, "GeForce RTX 3090", "Karty graficzne",
            "Liczba rdzeni CUDA: 10496, częstotliwość podwyższona: 1,70GHz,\n" +
            "pojemność pamięci: 24 GB, rodzaj pamięci: GDDR6X",
            "https://m.media-amazon.com/images/I/61w-oDCjydL.jpg", 6199.0f, (byte)1);
        Product p2 = new Product(0, "GeForce RTX 3080", "Karty graficzne",
            "Liczba rdzenia CUDA: 10420, częstotliwość podwyższona: 1,67GHz,\n" +
            "pojemność pamięci: 12 GB, rodzaj pamięci: GDDR6X",
            "https://i.ebayimg.com/images/g/ChsAAOSwXrpfbI4~/s-l640.jpg", 2899.0f, (byte)1);
        productRepository.saveAll(Arrays.asList(p1, p2));
    }
}
