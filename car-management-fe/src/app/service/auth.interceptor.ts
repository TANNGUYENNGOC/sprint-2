import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import {TokenStorageService} from "./token-storage.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private tokenStorageService: TokenStorageService) {}

  intercept(request: HttpRequest<unknown>,
            next: HttpHandler): Observable<HttpEvent<unknown>> {
    if (this.tokenStorageService.getToken() != null) {
      const token = this.tokenStorageService.getToken();
      // @ts-ignore
      // const headers = new HttpHeaders().set('Authorization', 'Bearer ' + token); // cách 1
      const AuthRequest = request.clone({headers: request.headers.set('Authorization','Bearer ' + token)});// cách 2
      // const AuthRequest = request.clone({headers});
      return next.handle(AuthRequest);
    } else {
      return next.handle(request);
    }

  }
}
