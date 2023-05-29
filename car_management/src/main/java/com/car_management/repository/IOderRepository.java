package com.car_management.repository;

import com.car_management.dto.oder.IOderDTO;
import com.car_management.dto.oder.OderDTO;
import com.car_management.dto.oder_detail.IOderDetailDTO;
import com.car_management.model.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface IOderRepository extends JpaRepository<Orders,Integer> {
    //Lấy ra số lượng sản phẩm của khách hàng
    @Query(value = "select sum(cart.number_of_vehicles) as totalOder from cart join user u on u.id = cart.user_id where user_id=:idCustomer",
            countQuery = "select sum(cart.number_of_vehicles) as totalOder from cart join user u on u.id = cart.user_id where user_id=:idCustomer",
            nativeQuery = true)
    OderDTO totalOderCustomer(@Param("idCustomer") Integer idCustomer);

    //Lấy ra oders
    @Query(value = "select orders.id,orders.total_oder,orders.user_id from orders where user_id = :idCustomer",
    countQuery = "select orders.id,orders.total_oder,orders.user_id from orders where user_id = :idCustomer",
    nativeQuery = true)
    IOderDTO findByIdCustomerOder(@Param("idCustomer") Integer idCustomer);

    //Thêm mới vào bảng oders
    @Transactional
    @Modifying
    @Query(value = "insert into orders(user_id, total_oder) values (:idUser,:totalOder)",
    nativeQuery = true)
    void insertOder(@Param("idUser")Integer idUser,@Param("totalOder")Integer totalOder);

//    Thêm sản phẩm hoặc bớt sản phẩm vào oders
    @Transactional
    @Modifying
    @Query(value = "UPDATE orders SET total_oder = :totalOder WHERE orders.user_id = :idUser",
    nativeQuery = true)
    void updateOder(@Param("totalOder")Integer totalOder,@Param("idUser")Integer idUser);

    //Lấy ra id User mới thêm vào giỏ hàng
    @Query(value = "select MAX(cart.user_id) as maxId from cart group by user_id",
    nativeQuery = true)
    Integer idMax();

    //Lấy ra list sản phẩm có trong giỏ hàng của 1 user
    @Query(value = "select oder_detail.id as id, c.id as idCar,c.name nameCar, ct.name as nameCarType,cs.name as nameCarSeries,c.img as img,oder_detail.number_of_vehicles as numberOfVehicles, oder_detail.price as price from oder_detail join orders o on oder_detail.orders_id = o.id join car c on c.id = oder_detail.car_id join car_series cs on cs.id = c.car_series_id join car_type ct on ct.id = c.car_type_id where o.user_id = :idUser",
            countQuery = "select oder_detail.id as id, c.id as idCar,c.name nameCar, ct.name as nameCarType,cs.name as nameCarSeries,c.img as img,oder_detail.number_of_vehicles as numberOfVehicles, oder_detail.price as price from oder_detail join orders o on oder_detail.orders_id = o.id join car c on c.id = oder_detail.car_id join car_series cs on cs.id = c.car_series_id join car_type ct on ct.id = c.car_type_id where o.user_id = :idUser",
            nativeQuery = true)
    Page<IOderDetailDTO> getOrderDetail(@Param("idUser") Integer idUser, Pageable pageable);
    //Kiểm tra tồn tại order bằng idUser
    @Query(value = "select id,modify_date as modifyDate, total_oder as totalOder,user_id as idUser,create_date as createDate from orders where user_id = :idCustomer",
    countQuery = "select id,modify_date as modifyDate, total_oder as totalOder,user_id as idUser,create_date as createDate from orders where user_id = :idCustomer",
    nativeQuery = true)
    IOderDTO getOrder(@Param("idCustomer") Integer idCustomer);
    //Inser vào order khi khách hàng chưa có giỏ hàng
    @Modifying
    @Transactional
    @Query(value = "insert into orders(total_oder,user_id)values (:totalOder,:idUser)",
    nativeQuery = true)
    void insertOrder(@Param("totalOder") double totalOder,@Param("idUser") Integer idUser);
    //update lại order khi khách hàng đã có giỏ hàng
    @Modifying
    @Transactional
    @Query(value = "UPDATE orders SET orders.total_oder = :totalOder WHERE orders.user_id = :idUser;",
            nativeQuery = true)
    void updateOrder(@Param("totalOder") double totalOder,@Param("idUser") Integer idUser);

    @Transactional
    @Modifying
    @Query(value = "insert into oder_detail(orders_id,quantity,price) values (:orderId,:quantity,:price)",
    nativeQuery = true)
    void addOrderDetail(@Param("orderId") Integer orderId,@Param("quantity")Integer quantity,@Param("price")Double price);

    @Transactional
    @Modifying
    @Query(value = "update oder_detail set quantity = :quantity,price = :price where orders_id = :orderId",
    nativeQuery = true)
    void updateOrderDetail(@Param("quantity")Integer quantity,@Param("price") Double price,@Param("orderId") Integer orderId);
}
