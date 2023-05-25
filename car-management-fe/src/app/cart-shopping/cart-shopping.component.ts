import {Component, OnInit} from '@angular/core';
import {CartDTO} from "../dto/cart-dto";
import {CartService} from "../service/cart.service";
import {ProjectJson} from "../model/project-json";
import {TokenStorageService} from "../service/token-storage.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-cart-shopping',
  templateUrl: './cart-shopping.component.html',
  styleUrls: ['./cart-shopping.component.css']
})
export class CartShoppingComponent implements OnInit {
  cartList: CartDTO[] = [];
  teamPage: ProjectJson;
  page: number = 0;

  constructor(private cartService: CartService,
              private tokenStorageService: TokenStorageService) {
  }

  ngOnInit(): void {
    this.getAllCart(this.page);
  }

  getAllCart(page: number,) {
    this.cartService.getAllCart(page).subscribe(next => {
      this.cartList = next.content;
      this.teamPage = next;
      console.log(this.cartList)
    })
  }

  changePage(page: number) {
    this.getAllCart(page);
  }

  addCarCart(idCar: number) {
    // @ts-ignore
    let cartDTO: CartDTO = {
      carId: idCar,
      userId: this.tokenStorageService.getUser().idUser
    }
    this.cartService.addCart(cartDTO).subscribe(next => {
      this.getAllCart(this.page);
    }, error => {
      Swal.fire({
        icon: 'error',
        title: 'Hết hàng',
        text: 'Trở lại trang sản phẩm để chọn mặc hàng khác!',
        footer: '<a href="http://localhost:4200/list-car">Danh sách sản phẩm</a>'
      })
    })
  }

  deleteCarCart(carId: number) {
    // @ts-ignore
    let cartDTO: CartDTO = {
      carId: carId,
      userId: this.tokenStorageService.getUser().idUser
    }
    this.cartService.deleteCart(cartDTO).subscribe(next => {
      this.getAllCart(this.page);
    }, error => {
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Something went wrong!',
        footer: '<a href="">Why do I have this issue?</a>'
      })
    })
  }
}
