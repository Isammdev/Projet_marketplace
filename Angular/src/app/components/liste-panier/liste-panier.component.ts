import { Component, OnInit } from '@angular/core';
  import { Router } from '@angular/router';
import { Panier } from '../../models/panier';
import { ArticleService } from '../../services/article.service';
import { Commande } from '../../models/commande';
import { $ } from 'protractor';
@Component({
  selector: 'app-liste-panier',
  templateUrl: './liste-panier.component.html',
  styleUrls: ['./liste-panier.component.css'],
  providers: [ArticleService]
})
export class ListePanierComponent implements OnInit {
  paniers:Panier[] = new Array;
  private commande: Commande = new  Commande  ;
   constructor(private articleService: ArticleService,private router:Router) { }

  ngOnInit() {
  	this.articleService.getPanier().then(panier => this.paniers = panier);

  }


Supprimer(panierSelect:Panier):void{
this.articleService.SupprimerPanier(panierSelect.id).then(response =>
                {
                  this.paniers.splice(this.paniers.indexOf(panierSelect), 1);
                },
                error =>
                {
                    console.log(error);
                });

}

payer( ):void{
 
  this.articleService.payer(this.commande,this.getSum() ).subscribe(
    res => {
      this.paniers =new Array () ;
      

    },
    error => {
      console.log(error);
    }
  );}
 

  getSum() : number {
    let sum = 0;
    for(let i = 0; i < this.paniers.length; i++) {
      sum += (this.paniers[i].nombreArticle*this.paniers[i].article.articlePrix);
    }
 
    return sum;
  }

}
