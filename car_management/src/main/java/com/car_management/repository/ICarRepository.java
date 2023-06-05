package com.car_management.repository;

import com.car_management.dto.car.ICarDTO;
import com.car_management.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ICarRepository extends JpaRepository<Car, Integer> {
    @Query(value = "select c.id,c.img as url,c.name,c.price,c.number_of_seats as numberOfSeats,ct.name as carTypeName,c.fuel,c.origin,cs.name as carSeriesName, c.discount from car c join car_type ct on ct.id = c.car_type_id join car_series cs on cs.id = c.car_series_id where ct.id like concat('%',:idCarType,'%') and cs.id like concat('%',:idCarSeries,'%') and c.name like concat('%',:nameCar,'%')  order by c.id desc",
    countQuery = "select c.id,c.img as url,c.name,c.price,c.number_of_seats as numberOfSeats,ct.name as carTypeName,c.fuel,c.origin,cs.name as carSeriesName, c.discount from car c join car_type ct on ct.id = c.car_type_id join car_series cs on cs.id = c.car_series_id where ct.id like concat('%',:idCarType,'%') and cs.id like concat('%',:idCarSeries,'%') and c.name like concat('%',:nameCar,'%')  order by c.id desc",
    nativeQuery = true)
    Page<ICarDTO> listCar(Pageable pageable, @Param("idCarType") String idCarType,@Param("idCarSeries") String idCarSeries, @Param("nameCar") String nameCar);

    Car findCarById (Integer id);

}
