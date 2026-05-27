import { bootstrapApplication } from '@angular/platform-browser';
import { provideRouter, RouterLink, RouterOutlet } from '@angular/router';
import { Component } from '@angular/core';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { routes } from './app/app.routes';
import { authInterceptor } from './app/core/interceptors/auth.interceptor';
import { AuthService } from './app/core/services/auth.service';

@Component({selector:'app-root',standalone:true,imports:[RouterOutlet,RouterLink],template:`
<nav class="nav"><a routerLink="/"><b>MyStreeT</b></a><a routerLink="/cart">Cart</a><a routerLink="/orders">My Orders</a>@if(auth.isAdmin()){<a routerLink="/admin/products">Admin</a>}<span class="spacer"></span>@if(auth.isLoggedIn()){<span>{{auth.userEmail()}}</span><button class="btn secondary" (click)="auth.logout()">Logout</button>}@else{<a routerLink="/login">Login</a><a routerLink="/register">Register</a>}</nav><router-outlet></router-outlet>`})
class AppComponent{constructor(public auth:AuthService){}}
bootstrapApplication(AppComponent,{providers:[provideRouter(routes),provideHttpClient(withInterceptors([authInterceptor]))]}).catch(err=>console.error(err));
