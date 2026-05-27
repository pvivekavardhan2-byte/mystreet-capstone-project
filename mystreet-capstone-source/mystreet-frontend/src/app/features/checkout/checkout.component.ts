import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { CartService } from '../../core/services/cart.service';
import { OrderService } from '../../core/services/order.service';
@Component({selector:'app-checkout',standalone:true,imports:[FormsModule],template:`
<div class="container"><div class="card"><h2>Checkout</h2><p class="error">{{error}}</p><textarea [(ngModel)]="address" placeholder="Shipping address"></textarea><select [(ngModel)]="paymentMode"><option value="COD">Cash on Delivery</option><option value="MOCK_UPI">Mock UPI</option></select><h3>Total: ${{cart.total()}}</h3><button class="btn" (click)="place()">Place Order</button></div></div>`})
export class CheckoutComponent{address='';paymentMode:'COD'|'MOCK_UPI'='COD';error='';constructor(public cart:CartService,private orders:OrderService,private router:Router){} place(){const req={shippingAddress:this.address,paymentMode:this.paymentMode,items:this.cart.items().map(i=>({productId:i.product.id,size:i.size,quantity:i.quantity}))};this.orders.place(req).subscribe({next:o=>{this.cart.clear();this.router.navigate(['/orders'],{queryParams:{placed:o.id}})},error:e=>this.error=e.error?.message||'Order failed'});}}
