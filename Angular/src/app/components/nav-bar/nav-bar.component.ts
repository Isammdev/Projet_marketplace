import { Component, OnInit } from '@angular/core';
 import {Router} from '@angular/router';
import { PersonneService } from '../../services/personne.service';
import { Personne } from '../../models/Personne';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  private user: Personne = new Personne();

  private loggedIn = false;
  private Isvendeur = false;

  constructor(private loginService:PersonneService, private router:Router) { }

  toggleDisplay() {
  	this.loggedIn = !this.loggedIn;
  }

  logout() {
    this.loginService.logout().subscribe(
      res => {
        location.reload();
      },
      error => {
        console.log(error);
      }
    );

    this.router.navigate(['/']);
  }

  ngOnInit() {
    this.loginService.checkSession().subscribe(
      res => {
        this.loggedIn=true;
        this.loginService.getCurrentUser().subscribe(
          res => {
            this.user = res.json();
            if (this.user.personneRole =="Acheteur")
            this.Isvendeur=false;
            else
            this.Isvendeur=true;
            
    
      
           },
          err => {
            console.log(err);
          }
        );
      },
      error => {
        this.loggedIn=false;
      }
    );
  }

}
