package com.car_management.service.cart;

import com.car_management.dto.cart.CartDTO;
import com.car_management.dto.cart.ICartDTO;
import com.car_management.model.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface ICartService {
    Page<ICartDTO> cartList(int idCustomer, Pageable pageable);

    void addCart(CartDTO cartDTO);

    void save(Cart cart);

    Cart findById(Integer id);

    ICartDTO checkForExistence(Integer idCustomer,Integer idCar);



}
