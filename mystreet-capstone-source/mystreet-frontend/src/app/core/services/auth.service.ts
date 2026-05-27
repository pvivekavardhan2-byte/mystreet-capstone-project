import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { tap } from 'rxjs';
import { AuthResponse } from '../../shared/models/models';

@Injectable({providedIn:'root'})
export class AuthService{
  private api='http://localhost:8080/api/auth';
  constructor(private http:HttpClient, private router:Router){}
  register(email:string,password:string){return this.http.post<AuthResponse>(`${this.api}/register`,{email,password}).pipe(tap(r=>this.save(r)));}
  login(email:string,password:string){return this.http.post<AuthResponse>(`${this.api}/login`,{email,password}).pipe(tap(r=>this.save(r)));}
  save(r:AuthResponse){localStorage.setItem('token',r.token);localStorage.setItem('email',r.email);localStorage.setItem('admin',String(r.admin));}
  token(){return localStorage.getItem('token');}
  isLoggedIn(){return !!this.token();}
  isAdmin(){return localStorage.getItem('admin')==='true';}
  userEmail(){return localStorage.getItem('email')||'';}
  logout(){localStorage.removeItem('token');localStorage.removeItem('email');localStorage.removeItem('admin');this.router.navigate(['/login']);}
}
