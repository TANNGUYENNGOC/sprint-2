package com.car_management.service.oder;

import com.car_management.dto.oder.IOderDTO;
import com.car_management.dto.oder.OderDTO;
import com.car_management.dto.oder_detail.IOderDetailDTO;
import com.car_management.dto.oder_detail.IOderDetailDTO1;
import com.car_management.model.Orders;
import com.car_management.repository.IOderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OderServiceImpl implements IOderService {
    @Autowired
    private IOderRepository iOderRepository;

    @Override
    public OderDTO totalOderCustomer(Integer idCustomer) {
        return iOderRepository.totalOderCustomer(idCustomer);
    }

    @Override
    public void insertOder(Integer idUser, Integer totalOder) {
        iOderRepository.insertOder(idUser, totalOder);
    }

    @Override
    public void updateOder(Integer totalOder, Integer idUser) {
        iOderRepository.updateOder(totalOder, idUser);
    }

    @Override
    public IOderDTO findByIdCustomerOder(Integer idCustomer) {
        return iOderRepository.findByIdCustomerOder(idCustomer);
    }

    @Override
    public void save(Orders orders) {
        iOderRepository.save(orders);
    }

    @Override
    public void deleteById(Integer id) {
        iOderRepository.deleteById(id);
    }

    @Override
    public Orders findById(Integer id) {
        return iOderRepository.findById(id).get();
    }

    @Override
    public Integer idMax() {
        return iOderRepository.idMax();
    }

    @Override
    public Page<IOderDetailDTO> getOrderDetail(Integer idUser, Pageable pageable) {
        return iOderRepository.getOrderDetail(idUser, pageable);
    }

    @Override
    public IOderDTO getOrder(Integer idCustomer) {
        return iOderRepository.getOrder(idCustomer);
    }

    @Override
    public void insertOrder(double totalOder, Integer idUser) {
        iOderRepository.insertOrder(totalOder, idUser);
    }

    @Override
    public void updateOrder(double totalOder, Integer idUser) {
        iOderRepository.updateOrder(totalOder, idUser);
    }

    @Override
    public void addOrderDetail(Integer orderId, Integer quantity, Double price) {
        iOderRepository.addOrderDetail(orderId, quantity, price);
    }


    @Override
    public void updateOrderDetail(Integer quantity, Double price, Integer orderId) {
        iOderRepository.updateOrderDetail(quantity, price, orderId);
    }

    @Override
    public IOderDetailDTO1 selectOrderDetaiByIdCustomer(Integer idCustomer) {
        return iOderRepository.selectOrderDetaiByIdCustomer(idCustomer);
    }

    @Override
    public void deleteOrdersDetail(Integer idOderDetail) {
        iOderRepository.deleteOrdersDetail(idOderDetail);
    }

    @Override
    public void deleteOrder(Integer idUser) {
        iOderRepository.deleteOrder(idUser);
    }


}
