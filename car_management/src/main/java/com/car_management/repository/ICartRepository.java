package com.car_management.repository;

import com.car_management.model.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ICartRepository extends JpaRepository<Cart,Integer> {
    @Query(value = "select * from customer c join cart c2 on c.id = c2.customer_id join car c3 on c3.id = c2.car_id join car_type ct on ct.id = c3.car_type_id join car_series cs on cs.id = c3.car_series_id where c.id = :idCustomer",
    countQuery = "select * from customer c join cart c2 on c.id = c2.customer_id join car c3 on c3.id = c2.car_id join car_type ct on ct.id = c3.car_type_id join car_series cs on cs.id = c3.car_series_id where c.id = :idCustomer",
    nativeQuery = true)
    Page<Cart> cartList(@Param("idCustomer") int idCustomer, Pageable pageable);
}
