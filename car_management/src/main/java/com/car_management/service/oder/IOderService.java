package com.car_management.service.oder;

import com.car_management.dto.oder.IOderDTO;
import com.car_management.dto.oder.OderDTO;
import com.car_management.dto.oder_detail.IOderDetailDTO;
import com.car_management.model.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface IOderService {
    OderDTO totalOderCustomer(Integer idCustomer);
    void insertOder(Integer idUser,Integer totalOder);
    void updateOder(Integer totalOder,Integer idUser);
    IOderDTO findByIdCustomerOder( Integer idCustomer);

    void save(Orders orders);
    void deleteById(Integer id);
    Orders findById(Integer id);
    Integer idMax();

    Page<IOderDetailDTO> getOrderDetail( Integer idUser, Pageable pageable);
    IOderDTO getOrder(Integer idCustomer);
    void insertOrder( double totalOder, Integer idUser);
    void updateOrder( double totalOder,Integer idUser);
    void addOrderDetail(Integer orderId,Integer quantity,Double price);

    void updateOrderDetail(Integer quantity,@Param("price") Double price,@Param("orderId") Integer orderId);







}
