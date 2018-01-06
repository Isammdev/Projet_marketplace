import { Component, OnInit } from '@angular/core';
 
import { Router } from '@angular/router';
import { Favori } from '../../models/favori';
import { ArticleService } from '../../services/article.service';
@Component({
  selector: 'app-liste-favori',
  templateUrl: './liste-favori.component.html',
  styleUrls: ['./liste-favori.component.css'],
  providers: [ArticleService]
})
export class ListeFavoriComponent implements OnInit {
  favori:Favori[];
  fav:Favori;
  constructor(private articleService: ArticleService,private router:Router) { }

  ngOnInit() {
  	this.articleService.getFavori().then(favori => this.favori = favori);

  }
Supprimer(favori:Favori):void{
this.articleService.SupprimerFavori(favori.id).then(response =>
                {
                    console.log(response);
                    this.favori.splice(this.favori.indexOf(favori), 1);


                },
                error =>
                {
                    console.log(error);
                });


}
}
