package com.car_management.repository;

import com.car_management.model.CarType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarTypeRepository extends JpaRepository<CarType,Integer> {
}
