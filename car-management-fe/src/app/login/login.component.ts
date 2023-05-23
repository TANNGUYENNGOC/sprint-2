import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {LoginService} from "../service/login.service";
import {Login} from "../model/login";
import Swal from "sweetalert2";
import {Customer} from "../model/customer";
import {TokenStorageService} from "../service/token-storage.service";
import {Router} from "@angular/router";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup = new FormGroup({
    username: new FormControl(),
    password: new FormControl(),
  });

  emailInput: FormGroup = new FormGroup({
    email: new FormControl(),
  })

  constructor(private loginService: LoginService,
              private tokenStorageService: TokenStorageService,
              private router: Router) {
  }

  ngOnInit(): void {
  }

  login() {
    const login: Login = this.loginForm.value;
    console.log(login)
    this.loginService.logIn(login).subscribe(next => {
      this.tokenStorageService.saveTokenLocal(next.token);
      this.tokenStorageService.saveUserLocal(next);
      Swal.fire({
        position: 'top-end',
        icon: 'success',
        title: 'Đăng nhập thành công',
        showConfirmButton: false,
        timer: 1500
      });
      this.router.navigateByUrl('');
    }, error => {
      Swal.fire({
        icon: 'error',
        title: 'Mật khẩu không chính xác.',
        text: 'Vui lòng đăng nhập lại!',
        footer: ''
      })
    })
  }
}
