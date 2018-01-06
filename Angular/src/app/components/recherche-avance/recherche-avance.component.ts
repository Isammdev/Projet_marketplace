import { Component, OnInit } from '@angular/core';
 
import {ActivatedRoute, NavigationExtras} from "@angular/router";
import { Router } from '@angular/router';
import { ArticleService } from '../../services/article.service';
import { Article } from '../../models/Article';
@Component({
  selector: 'app-recherche-avance',
  templateUrl: './recherche-avance.component.html',
  styleUrls: ['./recherche-avance.component.css'],
  providers: [ArticleService]
})
export class RechercheAvanceComponent implements OnInit {
 private articles: Article[];
 article:Article;
 boutique:string;
 articleFilter: any ;
  constructor(private articleService: ArticleService,private route: ActivatedRoute,private router:Router) { }

  ngOnInit() {

 this.articleService. getListeArticle().then(articles => {this.articles = articles});
 this.articleFilter = { articlePrix: '' };
 this.articleFilter = { articleCategorie: '' };
 this.articleFilter = { articleDescription: '' };
  }

DetailArticle(article: Article): void{
        
       

      let navigationExtras: NavigationExtras= {
          queryParams: {
            "articleID": article.articleID
          }
      }
      this.router.navigate(['/DetailArticle'],navigationExtras);

}
}