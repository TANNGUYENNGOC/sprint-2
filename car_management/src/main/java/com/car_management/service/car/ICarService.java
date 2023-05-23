package com.car_management.service.car;

import com.car_management.dto.car.ICarDTO;
import com.car_management.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICarService {
    Page<ICarDTO> listCar(Pageable pageable, String idCarType, String idCarSeries,  String nameCar);

    Car findById(int id);

    Car findCarById (Integer id);

    List findAllCarType();
    List findAllCarSeries();
}
