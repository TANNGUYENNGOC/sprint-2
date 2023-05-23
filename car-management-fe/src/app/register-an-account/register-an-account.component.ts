import { Component, OnInit } from '@angular/core';
import {FormGroup} from "@angular/forms";

@Component({
  selector: 'app-register-an-account',
  templateUrl: './register-an-account.component.html',
  styleUrls: ['./register-an-account.component.css']
})
export class RegisterAnAccountComponent implements OnInit {
  registerAnAccountForm: FormGroup = new FormGroup({});
  constructor() { }

  ngOnInit(): void {
  }

}
