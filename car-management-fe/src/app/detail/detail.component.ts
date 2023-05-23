import {Component, OnInit} from '@angular/core';
import {Car} from "../model/car";
import {CarService} from "../service/car.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {
  car: Car;

  constructor(private carService: CarService,
              private activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.getCar();
  }

  getCar() {
    this.activatedRoute.paramMap.subscribe(next => {
      const id = parseInt(next.get('id'));
      this.carService.findById(id).subscribe(data =>{
        this.car = data;
      })
    })
  }

}
