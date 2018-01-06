import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
 
import {AppConst} from '../../constants/app-const';
import { PersonneService } from '../../services/personne.service';
import { Personne } from '../../models/Personne';

@Component({
  selector: 'app-my-account',
  templateUrl: './my-account.component.html',
  styleUrls: ['./my-account.component.css']
})
export class MyAccountComponent implements OnInit {
	selectedvendeur: boolean;

  private serverPath = AppConst.serverPath;
  private loginError:boolean = false;
  private loggedIn = false;
  private credential = {'username':'', 'password':''};
  private user: Personne = new Personne();
  
  private emailSent: boolean =false;
  private usernameExists:boolean;
  private emailExists:boolean;
  private username:string;
  private email:string ="";
  private role:string="";
  private boutiqueNom:string="";
  private boutiqueAdresse:string="";
  
  
  
  
  private emailNotExists: boolean =false;
  private forgetPasswordEmailSent: boolean;
  private recoverEmail:string;  

  constructor(
  	private personneService: PersonneService,
   	private router: Router
  	) { }
	  getCurrentUser() {
	
	}
  onLogin() {
  	this.personneService.login(this.credential.username, this.credential.password,).subscribe(
  		res => {
  			console.log(res);
  			localStorage.setItem("xAuthToken", res.json().token);
  			this.loggedIn = true;
			  location.reload();
			  this.personneService.getCurrentUser().subscribe(
				res => {
					this.user = res.json();
					if (this.user.personneRole =="acheteur")
					this.router.navigate(['/home']);
					else
					this.router.navigate(['/Myarticles']);
					
	
	  
				 },
				err => {
					console.log(err);
				}
			);			  
  			this.router.navigate(['/home']);
  		}, 
  		error => {
  			this.loggedIn = false;
  			this.loginError = true;
  		}
  	);
  }

  onNewAccount() {
  	this.usernameExists = false;
  	this.emailExists = false;
  	this.emailSent = false;

  	this.personneService.ajoutPersonne(this.username, this.email,this.role,this.boutiqueAdresse,this.boutiqueNom).subscribe(
  		res => {
  			console.log(res);
  			this.emailSent = true;
  		}, 
  		error => {
  			console.log(error.text());
  			let errorMessage = error.text();
  			if(errorMessage==="usernameExists") this.usernameExists=true;
  			if(errorMessage==="emailExists") this.emailExists=true;
  		}
  	);
  }
  select(e: string): void   
  {  
  if (e == "vendeur")
		  this.selectedvendeur = true;  
		  else{
			  this.role="acheteur";
			  this.boutiqueNom="";
			  this.boutiqueAdresse="";
		  this.selectedvendeur = false;  
		  
		}
  }  
  onForgetPassword() {
  	this.forgetPasswordEmailSent = false;
  	this.emailNotExists = false;

  	this.personneService.retrievePassword(this.recoverEmail).subscribe(
  		res => {
  			console.log(res);
  			this.forgetPasswordEmailSent = true;
  		},
  		error => {
  			console.log(error.text());
  			let errorMessage = error.text();
  			if(errorMessage==="Email not found") this.emailNotExists=true;
  		}
  	);
  }

  ngOnInit() {
  	this.personneService.checkSession().subscribe(
  		res => {
  			this.loggedIn = true;
  		},
  		error => {
  			this.loggedIn = false;
  		}
  	);
  }

}
