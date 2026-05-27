import { Injectable, signal } from '@angular/core';
import { CartItem, Product } from '../../shared/models/models';
@Injectable({providedIn:'root'})
export class CartService{
  items=signal<CartItem[]>(this.load());
  add(product:Product,size:string){const copy=[...this.items()]; const found=copy.find(i=>i.product.id===product.id&&i.size===size); found?found.quantity++:copy.push({product,size,quantity:1}); this.save(copy);}
  update(productId:string,size:string,quantity:number){const copy=this.items().map(i=>i.product.id===productId&&i.size===size?{...i,quantity}:i).filter(i=>i.quantity>0); this.save(copy);}
  remove(productId:string,size:string){this.save(this.items().filter(i=>!(i.product.id===productId&&i.size===size)));}
  clear(){this.save([]);}
  total(){return this.items().reduce((s,i)=>s+i.product.price*i.quantity,0);}
  private load(){return JSON.parse(localStorage.getItem('cart')||'[]') as CartItem[];}
  private save(items:CartItem[]){localStorage.setItem('cart',JSON.stringify(items));this.items.set(items);}
}
