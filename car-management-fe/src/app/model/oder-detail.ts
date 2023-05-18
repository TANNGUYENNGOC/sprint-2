import {Car} from "./car";
import {Orders} from "./orders";

export interface OderDetail {
  id?:number;
  flag?:boolean;
  quantity?:number;
  car?:Car;
  orders?:Orders;
  price?:number;
}
