import {CarType} from "./car-type";
import {CarSeries} from "./car-series";

export interface Car {
  id?: number;
  name?:string;
  carType?: CarType;
  price?:number;
  fuel?:string;
  discount?:number;
  carSeries?:CarSeries;
  origin?:string;
  numberOfSeats?:number;

}
