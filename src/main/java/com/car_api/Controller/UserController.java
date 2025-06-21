package com.car_api.Controller;
import com.car_api.dto.CarResponse;
import com.car_api.dto.LoginRequest;
import com.car_api.dto.RegisterRequest;
import com.car_api.entity.Car;
import com.car_api.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }
    @DeleteMapping("/delete-user")
    public ResponseEntity<String> deleteUser(@RequestBody Map<String, String> body) {
        String phone = body.get("phone");
        return ResponseEntity.ok(userService.deleteUserByPhone(phone));
    }




    @PostMapping("/car-by-phone")
    public ResponseEntity<CarResponse> getCarByPhone(@RequestBody Map<String, String> body) {
        String phone = body.get("phone");
        return ResponseEntity.ok(userService.getCarByPhone(phone));
    }

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getAllCars() {
        return ResponseEntity.ok(userService.getAllCars());
    }
}



