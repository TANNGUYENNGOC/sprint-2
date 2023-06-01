import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {TokenStorageService} from "./token-storage.service";
import {User} from "../model/user";
import {HistoryDTO} from "../dto/history-dto";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient:HttpClient,
              private tokenStorageService:TokenStorageService) { }

  getUser():Observable<any> {
    let idUser = this.tokenStorageService.getUser().idUser;
    return this.httpClient.get<User>("http://localhost:8080/api-user/get-user?idUser="+idUser);
  }
  getHistory(page:number):Observable<any>{
    let idUser = this.tokenStorageService.getUser().idUser;
    return this.httpClient.get<HistoryDTO[]>("http://localhost:8080/api-user/history?page="+page+"&idUser="+idUser);

  }
}
