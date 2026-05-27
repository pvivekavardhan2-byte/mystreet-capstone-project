import { Component, OnInit, signal } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { OrderService } from '../../core/services/order.service';
import { OrderResponse } from '../../shared/models/models';
@Component({selector:'app-orders',standalone:true,template:`<div class="container"><h2>My Orders</h2>@if(placed){<p class="success">Order placed successfully. Order ID: {{placed}}</p>}@for(o of orders(); track o.id){<div class="card"><h3>Order {{o.id}}</h3><p>Status: {{o.status}} | Total: ${{o.totalAmount}}</p><p>{{o.shippingAddress}}</p><ul>@for(i of o.items; track i.productId+i.size){<li>{{i.productName}} - Size {{i.size}} - Qty {{i.quantity}}</li>}</ul></div>}</div>`})
export class OrdersComponent implements OnInit{orders=signal<OrderResponse[]>([]);placed='';constructor(private service:OrderService,private route:ActivatedRoute){} ngOnInit(){this.placed=this.route.snapshot.queryParamMap.get('placed')||'';this.service.mine().subscribe(o=>this.orders.set(o));}}
