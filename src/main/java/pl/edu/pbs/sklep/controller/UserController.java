package pl.edu.pbs.sklep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.pbs.sklep.model.entities.User;
import pl.edu.pbs.sklep.repository.UserRepository;

import java.util.List;

public class UserController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
