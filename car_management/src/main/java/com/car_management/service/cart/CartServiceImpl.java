package com.car_management.service.cart;

import com.car_management.dto.cart.CartDTO;
import com.car_management.dto.cart.ICartDTO;
import com.car_management.dto.oder.OderDTO;
import com.car_management.repository.ICartRepository;
import com.car_management.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private ICartRepository iCartRepository;

    @Override
    public Page<ICartDTO> cartList(int idCustomer, Pageable pageable) {
        return iCartRepository.cartList(idCustomer, pageable);
    }

    @Override
    public void addCart(CartDTO cartDTO) {
        iCartRepository.addCart(cartDTO.getCarId(),cartDTO.getUserId(),cartDTO.getSumPrice());
    }

    @Override
    public void save(Cart cart) {
        iCartRepository.save(cart);
    }


    @Override
    public Cart findById(Integer id) {
       return iCartRepository.findById(id).get();
    }

    @Override
    public ICartDTO checkForExistence(Integer idCustomer, Integer idCar) {
        return iCartRepository.checkForExistence(idCustomer,idCar);
    }

    @Override
    public void deleteById(Integer id) {
        iCartRepository.deleteById(id);
    }

    @Override
    public ICartDTO getSumPrice(Integer idCustomer) {
        return iCartRepository.getSumPrice(idCustomer);
    }

    @Override
    public Integer totalOderCustomer(Integer idCustomer) {
        return iCartRepository.totalOderCustomer(idCustomer);
    }

}
