package com.car_management.repository;

import com.car_management.dto.cart.ICartDTO;
import com.car_management.dto.oder.OderDTO;
import com.car_management.model.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ICartRepository extends JpaRepository<Cart,Integer> {
    @Query(value = "select cart.id,c.id as carId,cart.number_of_vehicles as numberOfVehicles,c.name as carName,ct.name as carType,cs.name as carSeries,c.img,cart.sum_price as sumPrice from cart join user u on u.id = cart.user_id join car c on c.id = cart.car_id join car_series cs on c.car_series_id = cs.id join car_type ct on c.car_type_id = ct.id where u.id =:idCustomer and cart.number_of_vehicles > 0",
    countQuery = "select cart.id,c.id as carId,cart.number_of_vehicles as numberOfVehicles,c.name as carName,ct.name as carType,cs.name as carSeries,c.img,cart.sum_price as sumPrice from cart join user u on u.id = cart.user_id join car c on c.id = cart.car_id join car_series cs on c.car_series_id = cs.id join car_type ct on c.car_type_id = ct.id where u.id =:idCustomer and cart.number_of_vehicles > 0",
    nativeQuery = true)
    Page<ICartDTO> cartList(@Param("idCustomer") int idCustomer, Pageable pageable);


    //Kiểm tra mặt hàng có tồn tại trong giỏ hàng hay chưa
    @Query(value = "select cart.id,cart.number_of_vehicles as numberOfVehicles,c.name as carName,ct.name as carType,cs.name as carSeries,c.img from cart join user u on u.id = cart.user_id join car c on c.id = cart.car_id join car_series cs on c.car_series_id = cs.id join car_type ct on c.car_type_id = ct.id where u.id =:idCustomer and c.id = :idCar",
    countQuery = "select cart.id,cart.number_of_vehicles as numberOfVehicles,c.name as carName,ct.name as carType,cs.name as carSeries,c.img from cart join user u on u.id = cart.user_id join car c on c.id = cart.car_id join car_series cs on c.car_series_id = cs.id join car_type ct on c.car_type_id = ct.id where u.id =:idCustomer and c.id = :idCar",
    nativeQuery = true)
    ICartDTO checkForExistence(@Param("idCustomer")Integer idCustomer, @Param("idCar") Integer idCar);

    @Transactional
    @Modifying
    @Query(value = "insert into cart(car_id,user_id,sum_price) value (:carId,:userId,:sumPrice)",
    nativeQuery = true)
     void addCart(@Param("carId")int carId,@Param("userId") int userId, @Param("sumPrice") double sumPrice);


    //Lấy ra giá tổng sản phầm của 1 khách hàng
    @Query(value = "select sum(cart.sum_price) as sumPrice from cart join user u on u.id = cart.user_id where u.id = :idCustomer",
    countQuery = "select sum(cart.sum_price) as sumPrice from cart join user u on u.id = cart.user_id where u.id = :idCustomer",
    nativeQuery = true)
    ICartDTO getSumPrice(@Param("idCustomer") Integer idCustomer);



    //Lấy ra số lượng sản phẩm của khách hangg
    @Query(value = "select sum(cart.number_of_vehicles) as totalOder from cart join user u on u.id = cart.user_id where user_id=:idCustomer",
    countQuery = "select sum(cart.number_of_vehicles) as totalOder from cart join user u on u.id = cart.user_id where user_id=:idCustomer",
    nativeQuery = true)
    Integer totalOderCustomer(@Param("idCustomer") Integer idCustomer);
}
