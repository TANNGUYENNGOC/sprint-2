import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { BodyComponent } from './body/body.component';
import { FooterComponent } from './footer/footer.component';
import { DetailComponent } from './detail/detail.component';
import { CartShoppingComponent } from './cart-shopping/cart-shopping.component';
import { LoginComponent } from './login/login.component';
import { BookingComponent } from './booking/booking.component';
import {ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import { ListCarComponent } from './list-car/list-car.component';
import { RegisterAnAccountComponent } from './register-an-account/register-an-account.component';
import { CartManagementComponent } from './cart-management/cart-management.component';
import { PersonalInformationComponent } from './personal-information/personal-information.component';
import { HistoryComponent } from './history/history.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    BodyComponent,
    FooterComponent,
    DetailComponent,
    CartShoppingComponent,
    LoginComponent,
    BookingComponent,
    ListCarComponent,
    RegisterAnAccountComponent,
    CartManagementComponent,
    PersonalInformationComponent,
    HistoryComponent],
    imports: [
        BrowserModule,
        AppRoutingModule,
        ReactiveFormsModule,
      HttpClientModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
