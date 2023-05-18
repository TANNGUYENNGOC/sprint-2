import {Account} from "./account";

export interface Customer {
  id?:number;
  address?:string;
  dateOfBirth?:string;
  name?:string;
  email?:string;
  gender?:number;
  phoneNumber?:string;
  account?:Account;

}
