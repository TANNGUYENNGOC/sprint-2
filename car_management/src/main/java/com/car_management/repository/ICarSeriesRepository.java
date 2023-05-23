package com.car_management.repository;

import com.car_management.model.CarSeries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICarSeriesRepository extends JpaRepository<CarSeries,Integer> {
}
