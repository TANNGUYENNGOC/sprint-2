import {Account} from "./account";
import {Role} from "./role";


export interface AccountRole {
  id?:number;
  account?:Account;
  role?:Role;
}
