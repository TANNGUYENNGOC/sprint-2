import {Component, OnInit} from '@angular/core';
import {Car} from "../model/car";
import {CarService} from "../service/car.service";
import {ActivatedRoute} from "@angular/router";
import {CartDTO} from "../dto/cart-dto";
import Swal from "sweetalert2";
import {TokenStorageService} from "../service/token-storage.service";
import {CartService} from "../service/cart.service";

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {
  car: Car;

  constructor(private carService: CarService,
              private activatedRoute: ActivatedRoute,
              private tokenStorageService:TokenStorageService,
              private cartService:CartService) {
  }

  ngOnInit(): void {
    this.getCar();
  }

  getCar() {
    this.activatedRoute.paramMap.subscribe(next => {
      const id = parseInt(next.get('id'));
      this.carService.findById(id).subscribe(data =>{
        this.car = data;
      })
    })
  }

  addCarCart(idCar: number) {
    // @ts-ignore
    let cartDTO: CartDTO = {
      carId: idCar,
      userId: this.tokenStorageService.getUser().idUser
    }
    this.cartService.addCart(cartDTO).subscribe(next => {
      Swal.fire({
        icon: 'success',
        title: 'Thêm thành công vào giỏ hàng',
        showConfirmButton: false,
        timer: 1500
      })
    }, error => {
      Swal.fire({
        icon: 'error',
        title: 'Hết hàng',
        text: 'Trở lại trang sản phẩm để chọn mặc hàng khác!',
        footer: '<a href="http://localhost:4200/list-car">Danh sách sản phẩm</a>'
      })
    })
  }

}
