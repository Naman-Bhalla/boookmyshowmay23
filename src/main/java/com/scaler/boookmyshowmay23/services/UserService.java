package com.scaler.boookmyshowmay23.services;

import com.scaler.boookmyshowmay23.models.User;
import com.scaler.boookmyshowmay23.repositories.ShowRepository;
import com.scaler.boookmyshowmay23.repositories.UserRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public  User login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new RuntimeException();
        }

        User user = userOptional.get();

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return user;
        }

        throw new RuntimeException();
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public User signUp(String email, String password) {
        // SET TRANSACTION ISOLATION LEVEL SERIALIZABLE
        // BEGIN TRANSACTION
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            throw new RuntimeException();
        }

        User user = new User();
        user.setEmail(email);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setBookings(new ArrayList<>());

        User savedUser = userRepository.save(user);

        return savedUser;
        // COMMIT;
    }
}
