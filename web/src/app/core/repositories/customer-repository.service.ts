import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {Customer} from "../models/customer.model";
import {HttpClient} from "@angular/common/http";
import {ApiUrls} from "../../../environments/config";

@Injectable()
export class CustomerRepository {

  constructor(private http: HttpClient) {
  }

  public getCustomers(): Observable<Customer[]> {
    return this.http.get<Customer[]>(ApiUrls.GetCustomers);
  }
}
