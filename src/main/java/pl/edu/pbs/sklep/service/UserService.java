package pl.edu.pbs.sklep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pbs.sklep.model.entities.User;
import pl.edu.pbs.sklep.repository.UserRepository;
import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @PostConstruct
    public void init() {
        User u1 = new User(0, "admin", "admin", "admin@wp.pl", "123456789", "Jan", "Kowalski",
            LocalDateTime.of(2021, 10, 31, 12, 0, 0), LocalDateTime.of(2021, 10, 31, 12, 0, 0));
        userRepository.save(u1);
    }
}
