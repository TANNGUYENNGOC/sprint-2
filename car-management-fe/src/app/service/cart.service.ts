import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {TokenStorageService} from "./token-storage.service";
import {CartDTO} from "../dto/cart-dto";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private httpClient:HttpClient,
              private tokenStorageService:TokenStorageService) { }

  getAllCart(page:number):Observable<any> {
    let idCustomer:number = this.tokenStorageService.getUser().idUser;
    return this.httpClient.get<CartDTO[]>('http://localhost:8080/api-Cart/lisCart?page='+page+'&idCustomer='+idCustomer);
  }

  addCart(cartDTO:CartDTO):Observable<any>{
    return this.httpClient.post<CartDTO>('http://localhost:8080/api-Cart/addCart',cartDTO);
  }

  deleteCart(cartDTO:CartDTO):Observable<any>{
    return this.httpClient.post<CartDTO>('http://localhost:8080/api-Cart/deleteCart',cartDTO);
  }
}
