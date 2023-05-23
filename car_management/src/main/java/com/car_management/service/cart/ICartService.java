package com.car_management.service.cart;

import com.car_management.model.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface ICartService {
    Page<Cart> cartList(int idCustomer, Pageable pageable);
}
