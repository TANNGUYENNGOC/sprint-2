import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {DetailComponent} from "./detail/detail.component";
import {ListComponent} from "./list/list.component";
import {BodyComponent} from "./body/body.component";
import {BookingComponent} from "./booking/booking.component";
import {LoginComponent} from "./login/login.component";


const routes: Routes = [
  {path:'booking',component:BookingComponent},
  {path:'',component: BodyComponent},
  {path:'login',component:LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
