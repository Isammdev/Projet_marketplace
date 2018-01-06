import { Component, OnInit } from '@angular/core';
import { PersonneService } from '../../services/personne.service';
@Component({
  selector: 'app-compte',
  templateUrl: './compte.component.html',
  styleUrls: ['./compte.component.css'],
  providers: [PersonneService]
})

export class CompteComponent implements OnInit {
private username:string;
  private email:string;
  private passwordLogin:string;
  private emailLogin:string;
  constructor(private personneService: PersonneService) { }

  ngOnInit() {
  }


 onNewAccount() {
  	

  	this.personneService.ajoutPersonne2(this.username, this.email).subscribe(
  		res => {
  			console.log(res);
  			
  		}, 
  		error => {
  			console.log(error.text());
  			let errorMessage = error.text();
  			
  		}
  	);
  }




onLogin() {
    this.personneService.login(this.emailLogin, this.passwordLogin).subscribe(
      res => {
        console.log(res);
        localStorage.setItem("xAuthToken", res.json().token);
        
      }, 
      error => {
        
      }
    );
  }
}
