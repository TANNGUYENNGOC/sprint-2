import {Component, OnInit} from '@angular/core';
import {UserService} from "../service/user.service";
import {User} from "../model/user";
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'app-personal-information',
  templateUrl: './personal-information.component.html',
  styleUrls: ['./personal-information.component.css']
})
export class PersonalInformationComponent implements OnInit {

  constructor(private userService: UserService,
              private title:Title) {
    this.title.setTitle("Cá nhân")
  }

  user: User;

  ngOnInit(): void {
    this.getUser();
  }

  getUser() {
    this.userService.getUser().subscribe(next=>{
      this.user = next;
    })
  }

}
