import { Component, OnInit } from '@angular/core';
import {CustomerRepository} from "../../core/repositories/customer-repository.service";

@Component({
  selector: 'app-store',
  templateUrl: './store.component.html',
  styleUrls: ['./store.component.css']
})
export class StoreComponent implements OnInit {

  value: string;

  constructor(private customerRepository: CustomerRepository) { }

  ngOnInit() {
    this.customerRepository.getCustomers().subscribe(
      res => {this.value = JSON.stringify(res)},
      error => {alert(JSON.stringify(error))}
    )
  }

}
