import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, NavigationExtras} from "@angular/router";
import { Router } from '@angular/router';
import { PersonneService } from '../../services/personne.service';
import { Personne } from '../../models/Personne';
 
@Component({
  selector: 'app-detail-vendeur',
  templateUrl: './detail-vendeur.component.html',
  styleUrls: ['./detail-vendeur.component.css'],
  providers: [PersonneService]
})
export class DetailVendeurComponent implements OnInit {
vendeurID:number;
personne:Personne;
  constructor(private articleService: PersonneService,private route: ActivatedRoute,private router:Router) { }

  ngOnInit() {

  	this.route.queryParams.subscribe(params =>{
	  this.vendeurID= params["vendeurID"];
	  })


  	this.articleService.getVendeur(this.vendeurID).then(personne=> this.personne = personne);
  }
  message(personne:Personne) {
     this.router.navigate(['/message', this.personne.personneMail]);
  }
}
