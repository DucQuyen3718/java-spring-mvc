package vn.hecquyn.laptopshop.service;

import org.springframework.stereotype.Service;

import vn.hecquyn.laptopshop.domain.User;
import vn.hecquyn.laptopshop.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String handleHello() {
        return "Hello from Service";
    }

    public User handleSaveUser(User user) {
        User eric = this.userRepository.save(user);
        System.out.println(eric);
        return eric;
    }
}
