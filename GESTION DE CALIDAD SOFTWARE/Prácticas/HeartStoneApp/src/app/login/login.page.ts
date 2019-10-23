import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { debug } from 'util';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {
  email = '';
  password = '';

  onLoginForm = this.fb.group({
    email: ['',Validators.compose([
                    Validators.minLength(6),
                    Validators.email])],
    password: ['',Validators.compose([
                    Validators.minLength(6)])]
  });

  constructor(private fb:FormBuilder, private router:Router) { }

  goToHome(){
   // debugger;
    this.router.navigateByUrl('tabs/cards');
  }

  ngOnInit() {/*
    this.loginForm = new FormGroup({
      'email' : new FormControl(this.email, [
        Validators.required,
        Validators.email,
        Validators.minLength(5)
      ]),
      'password': new FormControl(this.password, [
        Validators.minLength(8),
        Validators.required
      ])
    })*/
  }

}
