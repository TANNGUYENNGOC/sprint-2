package com.car_management.controller;

import com.car_management.dto.cart.CartDTO;
import com.car_management.dto.cart.ICartDTO;
import com.car_management.model.Car;
import com.car_management.model.Cart;
import com.car_management.service.car.ICarService;
import com.car_management.service.cart.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api-Cart")
@CrossOrigin("*")
public class CartRestController {
    @Autowired
    private ICarService iCarService;
    @Autowired
    private ICartService iCartService;

    @GetMapping("lisCart")
    public ResponseEntity<Page<ICartDTO>> getListCart(@PageableDefault(value = 4) Pageable pageable,
                                                      @RequestParam(defaultValue = "") Integer idCustomer) {
        Page<ICartDTO> iCartDTOS = iCartService.cartList(idCustomer, pageable);
        return new ResponseEntity<>(iCartDTOS, HttpStatus.OK);
    }

    @PostMapping("addCart")
    public ResponseEntity addCart(@RequestBody CartDTO cartDTO) {
        ICartDTO iCartDTO = iCartService.checkForExistence(cartDTO.getUserId(), cartDTO.getCarId());
        if (iCartDTO != null) {
            Cart cart = iCartService.findById(iCartDTO.getId());
            Car car = iCarService.findById(cart.getCar().getId());
            if (car.getQuantity() <= 0) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            } else {
                cart.setNumberOfVehicles(cart.getNumberOfVehicles() + 1);
                iCartService.save(cart);
                car.setQuantity(car.getQuantity() - 1);
                iCarService.save(car);
                return new ResponseEntity(HttpStatus.OK);
            }

        } else if (iCartDTO == null) {
            Cart cart = iCartService.findById(iCartDTO.getId());
            Car car = iCarService.findById(cart.getCar().getId());
            if (car.getQuantity() <= 0) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
            iCartService.addCart(cartDTO);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("deleteCart")
    public ResponseEntity deleteCart(@RequestBody CartDTO cartDTO) {
        ICartDTO iCartDTO = iCartService.checkForExistence(cartDTO.getUserId(), cartDTO.getCarId());
        if (iCartDTO != null) {
            Cart cart = iCartService.findById(iCartDTO.getId());
            Car car = iCarService.findById(cart.getCar().getId());
            if (cart.getNumberOfVehicles() <= 0) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            } else {
                cart.setNumberOfVehicles(cart.getNumberOfVehicles() - 1);
                iCartService.save(cart);
                car.setQuantity(car.getQuantity() + 1);
                iCarService.save(car);
                return new ResponseEntity(HttpStatus.OK);
            }

        }
        return new ResponseEntity(HttpStatus.OK);
    }


}
