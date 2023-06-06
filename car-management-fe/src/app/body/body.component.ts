import { Component, OnInit } from '@angular/core';
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'app-body',
  templateUrl: './body.component.html',
  styleUrls: ['./body.component.css']
})
export class BodyComponent implements OnInit {

  constructor(private titleService: Title) {
    this.titleService.setTitle("Trang chủ")
  }

  ngOnInit(): void {
  }

}
