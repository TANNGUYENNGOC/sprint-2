package com.car_management.controller;

import com.car_management.dto.car.ICarDTO;
import com.car_management.model.Car;
import com.car_management.model.CarSeries;
import com.car_management.model.CarType;
import com.car_management.service.car.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api-Car")
@CrossOrigin("*")
public class CarRestController {
    @Autowired
    private ICarService iCarService;

    @GetMapping("listCar")
    public ResponseEntity<Page<ICarDTO>> getListCar(@PageableDefault(value = 6) Pageable pageable,
                                                    @RequestParam(defaultValue = "") String idCarType,
                                                    @RequestParam(defaultValue = "") String idCarSeries,
                                                    @RequestParam(defaultValue = "") String nameCar){
        Page<ICarDTO> iCarDTOS = iCarService.listCar(pageable,idCarType,idCarSeries,nameCar);
        if (iCarDTOS.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(iCarDTOS,HttpStatus.OK);
    }

    @GetMapping("detail")
    public ResponseEntity<Car> detail(@RequestParam Integer id){
//        Car car = iCarService.findById(id);
        Car car1 = iCarService.findCarById(id);
        if(car1 == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(car1,HttpStatus.OK);
    }

    @GetMapping("listCarType")
    public ResponseEntity<List<CarType>> getlistCarType(){
        List<CarType> carTypeList = iCarService.findAllCarType();
        if (carTypeList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(carTypeList,HttpStatus.OK);
    }

    @GetMapping("listCarSeries")
    public ResponseEntity<List<CarSeries>> getlistCarSeries(){
        List<CarSeries> CarSeries = iCarService.findAllCarSeries();
        if (CarSeries.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(CarSeries,HttpStatus.OK);
    }
}
