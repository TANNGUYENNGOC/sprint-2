import {Component, OnInit} from '@angular/core';
import {CarDTO} from "../dto/car-dto";
import {ProjectJson} from "../model/project-json";
import {CarService} from "../service/car.service";
import {CarType} from "../model/car-type";
import {CarSeries} from "../model/car-series";
import {CartService} from "../service/cart.service";
import {CartDTO} from "../dto/cart-dto";
import {TokenStorageService} from "../service/token-storage.service";

@Component({
  selector: 'app-list-car',
  templateUrl: './list-car.component.html',
  styleUrls: ['./list-car.component.css']
})
export class ListCarComponent implements OnInit {
  listCar: CarDTO [] = [];
  teamPage: ProjectJson;
  page: number;
  idCarType: string = "";
  idCarSeries: string = "";
  nameCar: string = "";

  listCarType: CarType [] = [];
  listCarSeries: CarSeries [] = [];


  constructor(private carService: CarService,
              private cartService: CartService,
              private tokenStorageService:TokenStorageService) {
  }

  ngOnInit(): void {
    this.getListCar(0, this.idCarType, this.idCarSeries, this.nameCar);
    this.getAllCarType();
    this.getAllCarSeries();
  }

  getListCar(page: number, idCarType: string, idCarSeries: string, nameCar: string) {
    this.page = page;
    this.idCarType = idCarType;
    this.idCarSeries = idCarSeries;
    this.nameCar = nameCar;
    console.log("idCarType: " + idCarType)
    console.log("idCarSeries: " + idCarSeries)
    console.log("nameCar: " + nameCar)
    this.carService.findAllCar(page, this.idCarType, this.idCarSeries, this.nameCar).subscribe(next => {
      this.listCar = next.content;
      this.teamPage = next;
    })
  }

  changePage(page: number) {
    this.getListCar(page, this.idCarType, this.idCarSeries, this.nameCar);

  }

  getAllCarType() {
    this.carService.findAllCarType().subscribe(next => {
      this.listCarType = next;
      console.log(this.listCarType)
    })
  }

  getAllCarSeries() {
    this.carService.findAllCarSeries().subscribe(next => {
      this.listCarSeries = next;
    })
  }

  addCart(idCar: number) {
    // @ts-ignore
    let cartDTO: CartDTO = {
      carId: idCar,
      userId: this.tokenStorageService.getUser().idUser
    }
    this.cartService.addCart(cartDTO).subscribe(next=>{
      console.log(cartDTO)
    })
  }
}
