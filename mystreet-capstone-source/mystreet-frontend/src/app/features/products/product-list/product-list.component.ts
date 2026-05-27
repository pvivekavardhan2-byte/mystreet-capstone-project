import { Component, OnInit, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { ProductService } from '../../../core/services/product.service';
import { CartService } from '../../../core/services/cart.service';
import { Product } from '../../../shared/models/models';

@Component({selector:'app-product-list',standalone:true,imports:[RouterLink,FormsModule],template:`
<div class="container"><h2>Sneaker Catalog</h2><div class="row"><input placeholder="Brand filter" [(ngModel)]="brand"><input placeholder="Size filter" [(ngModel)]="size"><button class="btn" (click)="load()">Apply</button></div>
<div class="grid">@for(p of products(); track p.id){<div class="card"><img [src]="p.imageUrl" [alt]="p.name"><h3>{{p.name}}</h3><p>{{p.brand}} - ${{p.price}}</p><p>Sizes: {{p.sizesCsv}}</p><a class="btn secondary" [routerLink]="['/products',p.id]">View</a> <button class="btn" (click)="cart.add(p, firstSize(p))">Add to cart</button></div>}</div></div>`})
export class ProductListComponent implements OnInit{
  products=signal<Product[]>([]); brand=''; size='';
  constructor(private service:ProductService, public cart:CartService){}
  ngOnInit(){this.load();}
  load(){this.service.list(this.brand||undefined,this.size||undefined).subscribe(data=>this.products.set(data));}
  firstSize(p:Product){return p.sizesCsv.split(',')[0];}
}
