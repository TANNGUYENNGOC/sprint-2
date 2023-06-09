import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Login} from "../model/login";

@Injectable({
  providedIn: 'root'
})


export class LoginService {
  constructor(private httpClient:HttpClient) { }


  logIn(login:Login) :Observable<any>{
     return  this.httpClient.post<Login>('http://localhost:8080/public/login',login);
      //  .subscribe(
      // resonse =>{
      //   // @ts-ignore
      //   localStorage.setItem('access_token', resonse.token);
      //
      //   // @ts-ignore
      //   console.log(resonse.token);
      // });
  }

  logOut(){
    window.localStorage.clear();
    window.sessionStorage.clear();
}
}
