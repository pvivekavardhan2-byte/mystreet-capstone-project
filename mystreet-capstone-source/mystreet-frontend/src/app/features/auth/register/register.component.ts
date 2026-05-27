import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../../core/services/auth.service';
@Component({selector:'app-register',standalone:true,imports:[FormsModule,RouterLink],template:`<div class="container"><div class="card"><h2>Register</h2><p class="error">{{error}}</p><input [(ngModel)]="email" placeholder="Email"><input [(ngModel)]="password" type="password" placeholder="Password"><button class="btn" (click)="submit()">Register</button><a routerLink="/login">Already have account?</a></div></div>`})
export class RegisterComponent{email='';password='';error='';constructor(private auth:AuthService,private router:Router){} submit(){this.auth.register(this.email,this.password).subscribe({next:()=>this.router.navigate(['/']),error:e=>this.error=e.error?.message||'Register failed'});}}
