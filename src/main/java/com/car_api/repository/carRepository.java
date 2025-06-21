package com.car_api.repository;

import com.car_api.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface carRepository extends JpaRepository<Car, Long> {
}
