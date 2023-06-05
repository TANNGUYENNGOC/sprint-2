import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from "../service/token-storage.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  token: string = localStorage.getItem('TOKEN_KEY');
  isLogIn: boolean = false;


  constructor(private tokenStorageService: TokenStorageService,
              private router: Router) {
  }

  user: any = this.tokenStorageService.getUser();

  ngOnInit(): void {
    this.checkLogIn();
  }

  checkLogIn() {
    this.isLogIn = this.tokenStorageService.isLogger()
  }

  logOut() {
    this.tokenStorageService.signOut();
    this.router.navigateByUrl('');
    window.location.reload();

  }


  cart() {
    let obj: any = this.tokenStorageService.getUser();
    if (this.isLogIn && obj.roles[0].authority == 'ROLE_USER') {
      this.router.navigateByUrl('/cart-shopping');
    } else if (this.isLogIn && obj.roles[0].authority == 'ROLE_ADMIN') {
      console.log(this.isLogIn && obj.roles[0].authority)

      this.router.navigateByUrl('/cart-management');
    }
  }
}
