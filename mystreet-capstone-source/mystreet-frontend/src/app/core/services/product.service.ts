import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Product } from '../../shared/models/models';

@Injectable({providedIn:'root'})
export class ProductService{
  private api='http://localhost:8080/api/products';
  constructor(private http:HttpClient){}
  list(brand?:string,size?:string){const params:any={}; if(brand)params.brand=brand; if(size)params.size=size; return this.http.get<Product[]>(this.api,{params});}
  get(id:string){return this.http.get<Product>(`${this.api}/${id}`);}
  create(p:Partial<Product>){return this.http.post<Product>(this.api,p);}
  update(id:string,p:Partial<Product>){return this.http.put<Product>(`${this.api}/${id}`,p);}
  delete(id:string){return this.http.delete<void>(`${this.api}/${id}`);}
}
