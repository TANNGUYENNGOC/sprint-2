package com.car_management.service.cart;

import com.car_management.repository.ICartRepository;
import com.car_management.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements ICartService{
    @Autowired
    private ICartRepository iCartRepository;

    @Override
    public Page<Cart> cartList(int idCustomer, Pageable pageable) {
        return iCartRepository.cartList(idCustomer,pageable);
    }

}
