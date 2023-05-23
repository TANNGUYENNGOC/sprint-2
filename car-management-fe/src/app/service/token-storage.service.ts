import { Injectable } from '@angular/core';

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {
  //Kiểm tra người dùng có đăng nhập hay không, nếu có trả về true, nếu không trả về false
  isLogger() {
    return !!this.getToken();
  }
//Phương thức này được sử dụng để đăng xuất người dùng. Nó xóa toàn bộ dữ liệu lưu trữ trong localStorage và sessionStorage.
  signOut() {
    window.localStorage.clear();
    window.sessionStorage.clear();
  }

  role(){

  }

  // Phương thức này được sử dụng để lưu trữ token đăng nhập vào localStorage.
  public saveTokenLocal(token: string) {
    window.localStorage.removeItem(TOKEN_KEY);
    window.localStorage.setItem(TOKEN_KEY, token);
  }

  // Phương thức này được sử dụng để lưu trữ token đăng nhập vào sessionStorage.
  public saveTokenSession(token: string) {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  // Phương thức này trả về token đăng nhập hiện tại. Nếu token tồn tại trong localStorage,
  // nó sẽ trả về token từ localStorage. Nếu không, nó sẽ trả về token từ sessionStorage.
  public getToken(): string {
    if (localStorage.getItem(TOKEN_KEY) !== null) {
      return localStorage.getItem(TOKEN_KEY);
    } else {
      return sessionStorage.getItem(TOKEN_KEY);
    }
  }

  // Phương thức này được sử dụng để lưu trữ thông tin người dùng vào localStorage sau
  // khi đăng nhập. Thông tin người dùng được chuyển đổi thành chuỗi JSON trước khi lưu trữ.
  public saveUserLocal(user) {
    window.localStorage.removeItem(USER_KEY);
    window.localStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  //Phương thức này được sử dụng để lưu trữ thông tin người dùng vào sessionStorage
  // sau khi đăng nhập. Thông tin người dùng được chuyển đổi thành chuỗi JSON trước khi lưu trữ.
  public saveUserSession(user) {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }
//Phương thức này trả về thông tin người dùng hiện tại. Nếu thông tin người dùng tồn tại trong
// localStorage, nó sẽ trả về thông tin người dùng từ localStorage. Nếu không, nó sẽ
// trả về thông tin người dùng từ sessionStorage.
  public getUser() {
    if (localStorage.getItem(USER_KEY) !== null) {
      return JSON.parse(localStorage.getItem(USER_KEY));
    } else {
      return JSON.parse(sessionStorage.getItem(USER_KEY));
    }
  }
}
