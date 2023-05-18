import {Customer} from "./customer";

export interface Orders {
  id?:number;
  customer?:Customer;
  totalOder?:number;
  createDate?:string;
  modifyDate?:string;
}
