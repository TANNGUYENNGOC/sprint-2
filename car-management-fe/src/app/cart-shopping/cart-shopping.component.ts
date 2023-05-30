import {Component, OnInit} from '@angular/core';
import {CartDTO} from "../dto/cart-dto";
import {CartService} from "../service/cart.service";
import {ProjectJson} from "../model/project-json";
import {TokenStorageService} from "../service/token-storage.service";
import Swal from "sweetalert2";
import {IOderDetailDTO} from "../dto/ioder-detail-dto";
import {OrderDetailDTO} from "../dto/order-detail-dto";

@Component({
  selector: 'app-cart-shopping',
  templateUrl: './cart-shopping.component.html',
  styleUrls: ['./cart-shopping.component.css']
})
export class CartShoppingComponent implements OnInit {
  cartList: IOderDetailDTO[] = [];
  teamPage: ProjectJson;
  page: number = 0;

  orderDetail: OrderDetailDTO;

  constructor(private cartService: CartService,
              private tokenStorageService: TokenStorageService) {
  }

  ngOnInit(): void {
    this.getAllCart(this.page);
 this.getOrderDetail();
  }
getOrderDetail(){
 this.cartService.getOrderDetail().subscribe(next=>{
   this.orderDetail = next;
 })
}

  getAllCart(page: number,) {
    this.cartService.getAllCart(page).subscribe(next => {
      this.cartList = next.content;
      this.teamPage = next;
      // console.log(this.cartList)
    },error => {
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Không có sản phẩm trong giỏ hàng',
        footer: '<a href="">Why do I have this issue?</a>'
      })
    })
  }

  changePage(page: number) {
    this.getAllCart(page);
  }

  pay(){
    // @ts-ignore
    let cartDTO: CartDTO = {
      carId: 0,
      userId: this.tokenStorageService.getUser().idUser
    }
    this.cartService.payToCart(cartDTO).subscribe(next=>{
      Swal.fire({
        position: 'top-end',
        icon: 'success',
        title: 'Thanh toán thành công',
        showConfirmButton: false,
        timer: 1500
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
      this.getAllCart(this.page);
      this.getOrderDetail();


    }, error => {
      Swal.fire({
        icon: 'error',
        title: 'Hết hàng',
        text: 'Trở lại trang sản phẩm để chọn mặc hàng khác!',
        footer: '<a href="http://localhost:4200/list-car">Danh sách sản phẩm</a>'
      })
    })
  }

  deleteCarCart(carId: number, numberOfVehicles) {
    if (numberOfVehicles > 1) {
      // @ts-ignore
      let cartDTO: CartDTO = {
        carId: carId,
        userId: this.tokenStorageService.getUser().idUser
      }
      this.cartService.deleteCart(cartDTO).subscribe(next => {
        this.getAllCart(this.page);
        this.getOrderDetail();
      })

    } else if (numberOfVehicles == 1 && Swal.fire({
      title: 'Do you want to save the changes?',
      showDenyButton: true,
      showCancelButton: false,
      confirmButtonText: 'Save',
      denyButtonText: `Don't save`,
    }).then((result) => {
      /* Read more about isConfirmed, isDenied below */
      if (result.isConfirmed) {
        Swal.fire('Saved!', '', 'success')
        // @ts-ignore
        let cartDTO: CartDTO = {
          carId: carId,
          userId: this.tokenStorageService.getUser().idUser
        }
        this.cartService.deleteCart(cartDTO).subscribe(next => {
          this.getAllCart(this.page);
          this.getOrderDetail();
        }, error => {
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Something went wrong!',
            footer: '<a href="">Why do I have this issue?</a>'
          })
        })
      } else if (result.isDenied) {
        Swal.fire('Changes are not saved', '', 'info')
        console.log(false)
      }
    })) {
    }
  }
}
