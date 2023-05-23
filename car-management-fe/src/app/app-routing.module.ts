import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {DetailComponent} from "./detail/detail.component";
import {BodyComponent} from "./body/body.component";
import {BookingComponent} from "./booking/booking.component";
import {LoginComponent} from "./login/login.component";
import {ListCarComponent} from "./list-car/list-car.component";
import {CartShoppingComponent} from "./cart-shopping/cart-shopping.component";
import {RegisterAnAccountComponent} from "./register-an-account/register-an-account.component";

import {CartManagementComponent} from "./cart-management/cart-management.component";
import {AddCommand} from "@angular/cli/commands/add-impl";
import {AdminGuard} from "./security/admin.guard";
import {UserGuard} from "./security/user.guard";


const routes: Routes = [
  {path: 'booking', component: BookingComponent},
  {path: '', component: BodyComponent},
  {path: 'login', component: LoginComponent},
  {path: 'detail/:id', component: DetailComponent},
  {path: 'list-car', component: ListCarComponent},
  {path: 'register-an-account', component: RegisterAnAccountComponent},

  {canActivate: [UserGuard]
    ,path: 'cart-shopping', component: CartShoppingComponent},
  {canActivate:[AdminGuard]
    ,path: 'cart-management', component: CartManagementComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
