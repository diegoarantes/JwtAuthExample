package com.chkcerto.labs;

import com.chkcerto.labs.entities.Role;
import com.chkcerto.labs.entities.User;
import com.chkcerto.labs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class LabsApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(LabsApplication.class, args);
    }

    public void run(String... args) {
        User adminAccount = userRepository.findByRole(Role.ADMIN);

        if(null == adminAccount) {
            User user = new User();

            user.setEmail("admin@gmail.com");
            user.setFirstName("Admin");
            user.setLastName("System");
            user.setRole(Role.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(user);

        }
    }

}
