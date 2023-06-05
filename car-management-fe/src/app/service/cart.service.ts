import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {TokenStorageService} from "./token-storage.service";
import {CartDTO} from "../dto/cart-dto";
import {IOderDetailDTO} from "../dto/ioder-detail-dto";
import {OrderDetailDTO} from "../dto/order-detail-dto";
import {CartDTO1} from "../dto/cart-dto1";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private httpClient:HttpClient,
              private tokenStorageService:TokenStorageService) { }

  getAllCart(page:number):Observable<any> {
    let idCustomer:number = this.tokenStorageService.getUser().idUser;
    return this.httpClient.get<IOderDetailDTO[]>('http://localhost:8080/api-Cart/lisCart?page='+page+'&idCustomer='+idCustomer);
  }

  addCart(cartDTO:CartDTO):Observable<any>{
    console.log(cartDTO)
    return this.httpClient.post<CartDTO>('http://localhost:8080/user/api-Cart/addCart',cartDTO);
  }

  deleteCart(cartDTO:CartDTO):Observable<any>{
    return this.httpClient.post<CartDTO>('http://localhost:8080/user/api-Cart/deleteCart',cartDTO);
  }

  getOrderDetail():Observable<any>{
    let idCustomer:number = this.tokenStorageService.getUser().idUser;
    return this.httpClient.get<OrderDetailDTO>('http://localhost:8080/user/api-Cart/oderDetail?idCustomer='+idCustomer);
  }

  payToCart(cartDTO:CartDTO):Observable<any>{
    console.log(cartDTO)
    let token = this.tokenStorageService.getToken();
    return this.httpClient.post<CartDTO>('http://localhost:8080/user/api-Cart/payCart',cartDTO);
  }

  deleteOneRecordCart(cart: IOderDetailDTO):Observable<any>{
    let cartDTO1:CartDTO1 = {
      id: cart.id,
      carId: cart.carId,
      carName:cart.carName,
      carType:cart.carType,
      carSeries:cart.carSeries,
      img:cart.img,
      numberOfVehicles:cart.numberOfVehicles,
      sumPrice: cart.sumPrice
    };
    let idCustomer:number = this.tokenStorageService.getUser().idUser;
    return this.httpClient.post<CartDTO1>('http://localhost:8080/user/api-Cart/deleteOneRecordCart?idCustomer='+idCustomer,cartDTO1);
  }
}
