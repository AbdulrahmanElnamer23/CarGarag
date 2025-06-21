package com.car_api.service;

import com.car_api.dto.CarResponse;
import com.car_api.dto.LoginRequest;
import com.car_api.dto.RegisterRequest;
import com.car_api.entity.Car;
import com.car_api.entity.Role;
import com.car_api.entity.User;
import com.car_api.repository.RoleRepository;
import com.car_api.repository.UserRepository;
import com.car_api.repository.carRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final carRepository carRepository;
    private final AuthenticationManager authenticationManager;

    @Autowired
    private RoleRepository roleRepository;

    public String register(RegisterRequest request) {
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        Role defaultRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Default role USER not found"));

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setSecondName(request.getSecondName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // ✅ بدون تشفير
        user.setPhone(request.getPhone());
        user.setCar(request.getCar());

        user.getRoles().add(defaultRole);

        userRepository.save(user);
        return "User registered successfully";
    }

    public String login(LoginRequest request) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            return "✅ Login successful";
        } catch (AuthenticationException e) {
            return "❌ Invalid email or password";
        }
    }

    public CarResponse getCarByPhone(String phone) {
        User user = userRepository.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Car car = user.getCar();
        return new CarResponse(car.getModel(), car.getPlateNumber(), car.getColor());
    }
    public String deleteUserByPhone(String phone) {
        User user = userRepository.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
        return "User deleted successfully";
    }



    public List<Car> getAllCars() {
        return carRepository.findAll();
    }
}
