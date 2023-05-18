import {Car} from "./car";
import {Customer} from "./customer";

export interface Cart {
  id?:number;
  car?:Car;
  customer?:Customer;
  numberOfVehicles?:number;
  status?:boolean;
  createDate?:string;
  datePay?:string;
  updateDate?:string;
}
