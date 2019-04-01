package com.filip.helloSpringSecurityOAuth2.service;


import com.filip.helloSpringSecurityOAuth2.entity.User;
import com.filip.helloSpringSecurityOAuth2.entity.UserRole;
import com.filip.helloSpringSecurityOAuth2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Arrays;

@Service
@Transactional
public class SignupService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @PostConstruct
    private void setupDefaultUser() {
        //-- just to make sure there is an ADMIN user exist in the database for testing purpose
        if (userRepository.count() == 0) {
            userRepository.save(new User("admin",
                    passwordEncoder.encode("adminpass"),
                    Arrays.asList(new UserRole("USER"), new UserRole("ADMIN"))));
        }
    }

}
