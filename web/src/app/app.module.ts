import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {CalculatorComponent} from "./features/calculator/calculator.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CustomerRepository} from "./core/repositories/customer-repository.service";
import {HttpClientModule} from "@angular/common/http";
import { StoreComponent } from './features/store/store.component';

@NgModule({
  declarations: [
    AppComponent,
    CalculatorComponent,
    StoreComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    CustomerRepository
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
