package com.car_management.repository;

import com.car_management.dto.history.IHistoryDTO;
import com.car_management.dto.history.IHistoryDTO1;
import com.car_management.dto.oder.IOderDTO;
import com.car_management.dto.oder.OderDTO;
import com.car_management.dto.oder_detail.IOderDetailDTO;
import com.car_management.dto.oder_detail.IOderDetailDTO1;
import com.car_management.model.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IOderRepository extends JpaRepository<Orders, Integer> {
    //Lấy ra số lượng sản phẩm của khách hàng
    @Query(value = "select sum(cart.number_of_vehicles) as totalOder from cart join user u on u.id = cart.user_id where user_id=:idCustomer and cart.status = false",
            countQuery = "select sum(cart.number_of_vehicles) as totalOder from cart join user u on u.id = cart.user_id where user_id=:idCustomer and cart.status = false",
            nativeQuery = true)
    OderDTO totalOderCustomer(@Param("idCustomer") Integer idCustomer);

    //Lấy ra oders
    @Query(value = "select orders.id,orders.total_oder,orders.user_id from orders where user_id = :idCustomer and orders.flag = false",
            countQuery = "select orders.id,orders.total_oder,orders.user_id from orders where user_id = :idCustomer and orders.flag = false",
            nativeQuery = true)
    IOderDTO findByIdCustomerOder(@Param("idCustomer") Integer idCustomer);

    //Thêm mới vào bảng oders
    @Transactional
    @Modifying
    @Query(value = "insert into orders(user_id, total_oder) values (:idUser,:totalOder)",
            nativeQuery = true)
    void insertOder(@Param("idUser") Integer idUser, @Param("totalOder") Integer totalOder);



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
    @Query(value = "select id,modify_date as modifyDate, total_oder as totalOder,user_id as idUser,create_date as createDate from orders where user_id = :idCustomer and orders.flag = false",
            countQuery = "select id,modify_date as modifyDate, total_oder as totalOder,user_id as idUser,create_date as createDate from orders where user_id = :idCustomer and orders.flag = false",
            nativeQuery = true)
    IOderDTO getOrder(@Param("idCustomer") Integer idCustomer);

    //Inser vào order khi khách hàng chưa có giỏ hàng
    @Modifying
    @Transactional
    @Query(value = "insert into orders(total_oder,user_id)values (:totalOder,:idUser)",
            nativeQuery = true)
    void insertOrder(@Param("totalOder") double totalOder, @Param("idUser") Integer idUser);
    //    Thêm sản phẩm hoặc bớt sản phẩm vào oders
    @Transactional
    @Modifying
    @Query(value = "UPDATE orders SET total_oder = :totalOder WHERE orders.user_id = :idUser and flag = false ",
            nativeQuery = true)
    void updateOder(@Param("totalOder") Integer totalOder, @Param("idUser") Integer idUser);
    //update lại order khi khách hàng đã có giỏ hàng
    @Modifying
    @Transactional
    @Query(value = "UPDATE orders SET orders.total_oder = :totalOder WHERE orders.user_id = :idUser;",
            nativeQuery = true)
    void updateOrder(@Param("totalOder") double totalOder, @Param("idUser") Integer idUser);

    @Transactional
    @Modifying
    @Query(value = "insert into oder_detail(orders_id,quantity,price) values (:orderId,:quantity,:price)",
            nativeQuery = true)
    void addOrderDetail(@Param("orderId") Integer orderId, @Param("quantity") Integer quantity, @Param("price") Double price);

    @Transactional
    @Modifying
    @Query(value = "update oder_detail set quantity = :quantity,price = :price where orders_id = :orderId",
            nativeQuery = true)
    void updateOrderDetail(@Param("quantity") Integer quantity, @Param("price") Double price, @Param("orderId") Integer orderId);

    //thông tin này dùng để thực hiện chức năng thanh toán
    @Query(value = "select oder_detail.id,oder_detail.price,oder_detail.quantity,orders_id as ordersId from oder_detail join orders o on oder_detail.orders_id = o.id where o.user_id = :idCustomer and oder_detail.flag = false",
            countQuery = "select oder_detail.id,oder_detail.price,oder_detail.quantity,orders_id as ordersId from oder_detail join orders o on oder_detail.orders_id = o.id where o.user_id = :idCustomer and oder_detail.flag = false",
            nativeQuery = true)
    IOderDetailDTO1 selectOrderDetaiByIdCustomer(@Param("idCustomer") Integer idCustomer);

    //Xóa oder_detail
    @Transactional
    @Modifying
    @Query(value = "delete from oder_detail where orders_id = :idOderDetail",
            nativeQuery = true)
    void deleteOrdersDetail(@Param("idOderDetail") Integer idOderDetail);

    //Xóa oder
    @Transactional
    @Modifying
    @Query(value = "delete from orders where user_id = :idUser and flag = false",
            nativeQuery = true)
    void deleteOrder(@Param("idUser") Integer idUser);

    //3 phương thức dùng để thanh toán
    @Transactional
    @Modifying
    @Query(value = "update cart set status = 1,date_pay = current_timestamp() where user_id = :idCustomer and status = false",
            nativeQuery = true)
    void payCart(@Param("idCustomer") Integer idCustomer);

    @Transactional
    @Modifying
    @Query(value = "update orders set flag = 1,modify_date = current_timestamp() where user_id = :idCustomer and flag = false",
            nativeQuery = true)
    void payOrders(@Param("idCustomer") Integer idCustomer);

    @Transactional
    @Modifying
    @Query(value = "update oder_detail set flag = 1 where orders_id = :idOrder",
            nativeQuery = true)
    void payOrderDetail(@Param("idOrder") Integer idOrder);

    //Lấy ra lịch sử
    @Query(value = "select oder_detail.id,oder_detail.price,oder_detail.quantity,o.create_date as createDate,o.modify_date as modifyDate from oder_detail join orders o on o.id = oder_detail.orders_id join user u on u.id = o.user_id where oder_detail.flag = true and u.id = :idCustomer order by orders_id desc",
            countQuery = "select oder_detail.id,oder_detail.price,oder_detail.quantity,o.create_date as createDate,o.modify_date as modifyDate from oder_detail join orders o on o.id = oder_detail.orders_id join user u on u.id = o.user_id where oder_detail.flag = true and u.id = :idCustomer order by orders_id desc"
            , nativeQuery = true)
    Page<IHistoryDTO> getHistory(@Param("idCustomer")Integer idCustomer, Pageable pageable);
    //CHi tiết lịch sử
    @Query(value = "select c2.name as nameCar, cs.name as seriesCar, ct.name carType, c.sum_price as sumPrice,c.number_of_vehicles as numberOfVehicles\n" +
            "from user u join orders o on u.id = o.user_id join oder_detail od on o.id = od.orders_id join cart c on u.id = c.user_id join car c2 on c2.id = c.car_id join car_type ct on ct.id = c2.car_type_id join car_series cs on cs.id = c2.car_series_id where od.id = :idOderDetail and o.modify_date = c.date_pay",
    nativeQuery = true)
    List<IHistoryDTO1> getHistory1(@Param("idOderDetail") Integer idOderDetail);
}
