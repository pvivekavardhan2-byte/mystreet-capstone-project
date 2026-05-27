import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { OrderRequest, OrderResponse } from '../../shared/models/models';
@Injectable({providedIn:'root'})
export class OrderService{
  private api='http://localhost:8080/api/orders';
  constructor(private http:HttpClient){}
  place(req:OrderRequest){return this.http.post<OrderResponse>(this.api,req);}
  mine(){return this.http.get<OrderResponse[]>(`${this.api}/mine`);}
}
