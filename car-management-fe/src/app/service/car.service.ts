import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {CarDTO} from "../dto/car-dto";
import {Car} from "../model/car";
import {CarType} from "../model/car-type";
import {CarSeries} from "../model/car-series";

@Injectable({
  providedIn: 'root'
})
export class CarService {

  constructor(private httpClient:HttpClient) { }

  findAllCar(page:number,idCarType:string,idCarSeries:string,nameCar:string):Observable<any>{
    console.log("idkieuxe: "+idCarType)
    return this.httpClient.get<CarDTO[]>('http://localhost:8080/api-Car/listCar?page='+page+'&idCarType='+idCarType+'&idCarSeries='+idCarSeries+'&nameCar='+nameCar);
    // return this.httpClient.get<CarDTO[]>('http://localhost:8080/api-Car/listCar?page='+page+'&idCarType='+6+'&idCarSeries='+idCarSeries+'&nameCar='+nameCar);
  };

  findById(id: number):Observable<any> {
    return this.httpClient.get<Car>('http://localhost:8080/api-Car/detail?id='+id);
  }

  findAllCarType():Observable<any>{
    // console.log(this.httpClient.get<CarType[]>("http://localhost:8080/api-Car/listCarType"))
    return this.httpClient.get<CarType[]>("http://localhost:8080/api-Car/listCarType");
  }
  findAllCarSeries():Observable<any> {
    return this.httpClient.get<CarSeries[]>("http://localhost:8080/api-Car/listCarSeries");
  }
}
