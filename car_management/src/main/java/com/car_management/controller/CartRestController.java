package com.car_management.controller;

import com.car_management.dto.cart.CartDTO;
import com.car_management.dto.cart.ICartDTO;
import com.car_management.dto.oder.IOderDTO;
import com.car_management.dto.oder.OderDTO;
import com.car_management.dto.oder_detail.IOderDetailDTO1;
import com.car_management.model.Car;
import com.car_management.model.Cart;
import com.car_management.model.Orders;
import com.car_management.service.car.ICarService;
import com.car_management.service.cart.ICartService;
import com.car_management.service.oder.IOderService;
import com.car_management.service.user.IUserService;
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
    @Autowired
    private IOderService iOderService;
    @Autowired
    private IUserService iUserService;

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

                cart.setSumPrice(cart.getNumberOfVehicles() * car.getPrice());
                iCartService.save(cart);

//                Lấy số lượng sản phẩm của 1 khách hàng, thao tác với oder
                int totalOder = iCartService.totalOderCustomer(cartDTO.getUserId());
                iOderService.updateOder(totalOder, cartDTO.getUserId());

                //update lại order_detail
                IOderDTO iOderDTO = iOderService.getOrder(cartDTO.getUserId());
                double price = iCartService.getSumPrice(cartDTO.getUserId()).getSumPrice();
                Integer totalAll = iCartService.totalOderCustomer(cartDTO.getUserId());
                iOderService.updateOrderDetail(totalAll, price, iOderDTO.getId());

                return new ResponseEntity(HttpStatus.OK);
            }

        } else if (iCartDTO == null) {
            Car car = iCarService.findById(cartDTO.getCarId());
            cartDTO.setSumPrice(car.getPrice());
            iCartService.addCart(cartDTO);

            IOderDTO iOderDTO = iOderService.findByIdCustomerOder(cartDTO.getUserId());
            if (iOderDTO == null) {
                //Thêm vào orders
//                int maxId = iOderService.idMax();
                int userId = cartDTO.getUserId();
                int totalOrder = iCartService.totalOderCustomer(cartDTO.getUserId());
                iOderService.insertOder(userId, totalOrder);

                //thêm mới order_detail
                IOderDTO iOderDTO1 = iOderService.getOrder(cartDTO.getUserId());
                double price = iCartService.getSumPrice(cartDTO.getUserId()).getSumPrice();
                Integer totalAll = iCartService.totalOderCustomer(cartDTO.getUserId());
                iOderService.addOrderDetail(iOderDTO1.getId(), totalAll, price);

            } else {
//                int maxId = iOderService.idMax();
                int totalOrder = iCartService.totalOderCustomer(cartDTO.getUserId());
                iOderService.updateOder(totalOrder, cartDTO.getUserId());

                //Update lại order_detail
                IOderDTO iOderDTO1 = iOderService.getOrder(cartDTO.getUserId());
                double price = iCartService.getSumPrice(cartDTO.getUserId()).getSumPrice();
                Integer totalAll = iCartService.totalOderCustomer(cartDTO.getUserId());
                iOderService.updateOrderDetail(totalAll, price, iOderDTO1.getId());
            }

        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("deleteCart")
    public ResponseEntity deleteCart(@RequestBody CartDTO cartDTO) {
        ICartDTO iCartDTO = iCartService.checkForExistence(cartDTO.getUserId(), cartDTO.getCarId());
        if (iCartDTO != null) {
            Cart cart = iCartService.findById(iCartDTO.getId());
            Car car = iCarService.findById(cart.getCar().getId());
            if (cart.getNumberOfVehicles() < 0) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            } else {
                cart.setNumberOfVehicles(cart.getNumberOfVehicles() - 1);
                iCartService.save(cart);
                if (cart.getNumberOfVehicles() == 0) {
                    iCartService.deleteById(cart.getId());
//                    int totalOder = iCartService.totalOderCustomer(cartDTO.getUserId());
                    Integer totalAll = iCartService.totalOderCustomer(cartDTO.getUserId());

                    if (totalAll == null) {
                        IOderDetailDTO1 iOderDetailDTO1 = iOderService.selectOrderDetaiByIdCustomer(cartDTO.getUserId());
                        iOderService.deleteOrdersDetail(iOderDetailDTO1.getOrdersId());
                        iOderService.deleteOrder(cartDTO.getUserId());
                    } else {
                        //Update lại order_detail
                        IOderDTO iOderDTO1 = iOderService.getOrder(cartDTO.getUserId());
                        double price = iCartService.getSumPrice(cartDTO.getUserId()).getSumPrice();
                        iOderService.updateOrderDetail(totalAll, price, iOderDTO1.getId());
                    }
                } else {
                    car.setQuantity(car.getQuantity() + 1);
                    iCarService.save(car);
                    cart.setSumPrice(cart.getNumberOfVehicles() * car.getPrice());
                    iCartService.save(cart);

                    //Thao tác với oder
                    int totalOder = iCartService.totalOderCustomer(cartDTO.getUserId());
                    iOderService.updateOder(totalOder, cartDTO.getUserId());
                    //update lại order_detail
                    IOderDTO iOderDTO = iOderService.getOrder(cartDTO.getUserId());
                    double price = iCartService.getSumPrice(cartDTO.getUserId()).getSumPrice();
                    Integer totalAll = iCartService.totalOderCustomer(cartDTO.getUserId());
                    iOderService.updateOrderDetail(totalAll, price, iOderDTO.getId());

                }
                return new ResponseEntity(HttpStatus.OK);

            }
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("oderDetail")
    public ResponseEntity<IOderDetailDTO1> getOderDetail(@RequestParam() Integer idCustomer) {
        IOderDetailDTO1 iOderDetailDTO1 = iOderService.selectOrderDetaiByIdCustomer(idCustomer);
        return new ResponseEntity<>(iOderDetailDTO1, HttpStatus.OK);
    }
}
