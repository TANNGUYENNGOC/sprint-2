package com.car_management.service.car;

import com.car_management.dto.car.ICarDTO;
import com.car_management.model.Car;
import com.car_management.repository.ICarRepository;
import com.car_management.repository.ICarSeriesRepository;
import com.car_management.repository.ICarTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements ICarService{
    @Autowired
    private ICarRepository iCarRepository;
    @Autowired
    private ICarTypeRepository iCarTypeRepository;
    @Autowired
    ICarSeriesRepository iCarSeriesRepository;


    @Override
    public Page<ICarDTO> listCar(Pageable pageable, String idCarType, String idCarSeries, String nameCar) {
        return iCarRepository.listCar(pageable,idCarType,idCarSeries,nameCar);
    }

    @Override
    public Car findById(int id) {
        return iCarRepository.findById(id).get();
    }

    @Override
    public Car findCarById(Integer id) {
        return iCarRepository.findCarById(id);
    }

    @Override
    public List findAllCarType() {
        return iCarTypeRepository.findAll();
    }

    @Override
    public List findAllCarSeries() {
        return iCarSeriesRepository.findAll();
    }

    @Override
    public void save(Car car) {
        iCarRepository.save(car);
    }


}
