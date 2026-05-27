import { Component, OnInit, signal } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { ProductService } from '../../../core/services/product.service';
import { CartService } from '../../../core/services/cart.service';
import { Product } from '../../../shared/models/models';
@Component({selector:'app-product-detail',standalone:true,imports:[FormsModule,RouterLink],template:`
<div class="container">@if(product()){<div class="card"><img style="max-width:420px;width:100%" [src]="product()!.imageUrl"><h2>{{product()!.name}}</h2><p><b>Brand:</b> {{product()!.brand}}</p><p>{{product()!.description}}</p><h3>${{product()!.price}}</h3><select [(ngModel)]="size">@for(s of sizes(); track s){<option [value]="s">{{s}}</option>}</select><button class="btn" (click)="add()">Add to cart</button> <a routerLink="/cart">Go to Cart</a></div>}</div>`})
export class ProductDetailComponent implements OnInit{
  product=signal<Product|null>(null); size='';
  constructor(private route:ActivatedRoute, private service:ProductService, private cart:CartService){}
  ngOnInit(){const id=this.route.snapshot.paramMap.get('id')!; this.service.get(id).subscribe(p=>{this.product.set(p); this.size=this.sizes()[0];});}
  sizes(){return this.product()?.sizesCsv.split(',')||[];}
  add(){const p=this.product(); if(p)this.cart.add(p,this.size);}
}
