package com.car_api;

import com.car_api.entity.Car;
import com.car_api.entity.Role;
import com.car_api.entity.User;
import com.car_api.repository.RoleRepository;
import com.car_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
@RequiredArgsConstructor
@Component
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder; // أضف هذا السطر

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            Car car = new Car(null, "Toyota", "ABC123", "White");

            Role roleUser = roleRepository.findByName("USER")
                    .orElseGet(() -> roleRepository.save(new Role(null, "USER", null)));

            User user = new User();
            user.setFirstName("mohamed");
            user.setSecondName("ahmed");
            user.setEmail("mohamedahmed@gmail.com");
            user.setPassword(passwordEncoder.encode("1234")); // ← هنا التشفير
            user.setPhone("0550001111");
            user.setCar(car);
            user.setRoles(Collections.singleton(roleUser));

            userRepository.save(user);
            System.out.println("✅ Sample user and car added to H2 database.");
        }
    }
}
