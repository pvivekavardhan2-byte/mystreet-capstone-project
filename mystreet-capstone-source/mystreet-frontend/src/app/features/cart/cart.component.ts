import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CartService } from '../../core/services/cart.service';
@Component({selector:'app-cart',standalone:true,imports:[RouterLink],template:`
<div class="container"><h2>Cart</h2>@if(cart.items().length===0){<p>Your cart is empty.</p>}@else{<table><tr><th>Item</th><th>Size</th><th>Qty</th><th>Price</th><th></th></tr>@for(i of cart.items(); track i.product.id+i.size){<tr><td>{{i.product.name}}</td><td>{{i.size}}</td><td><input type="number" [value]="i.quantity" min="1" style="width:80px" (change)="cart.update(i.product.id,i.size,+$any($event.target).value)"></td><td>${{i.product.price*i.quantity}}</td><td><button class="btn danger" (click)="cart.remove(i.product.id,i.size)">Remove</button></td></tr>}</table><h3>Total: ${{cart.total()}}</h3><a class="btn" routerLink="/checkout">Checkout</a>}</div>`})
export class CartComponent{constructor(public cart:CartService){}}
