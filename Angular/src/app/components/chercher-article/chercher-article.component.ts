import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ArticleService } from '../../services/article.service';
 import {ActivatedRoute, NavigationExtras} from "@angular/router";
 import { Article } from '../../models/Article';
 @Component({
  selector: 'app-chercher-article',
  templateUrl: './chercher-article.component.html',
  styleUrls: ['./chercher-article.component.css'],
  providers: [ArticleService]
})
export class ChercherArticleComponent implements OnInit {
  selectedArticle: Article;
  orderby: string;
 private articles: Article[];
  constructor(private articleService: ArticleService,private route: ActivatedRoute,private router:Router) { }

  ngOnInit() {

  }
  onSelect(article:Article) {

    let navigationExtras: NavigationExtras= {
      queryParams: {
        "articleID": article.articleID
      }
  }
  this.router.navigate(['/DetailArticle'],navigationExtras);
    
  }



Filtre() {
  	
this.articleService.getArticlesFiltres(this.orderby).then(articles => {this.articles = articles});
  	console.log(this.orderby)
  }



}
