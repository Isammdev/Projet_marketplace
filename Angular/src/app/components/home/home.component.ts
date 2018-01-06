import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ArticleService } from '../../services/article.service';
 import {ActivatedRoute, NavigationExtras} from "@angular/router";
 import { Article } from '../../models/Article';
 @Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [ArticleService]
})
export class HomeComponent implements OnInit {

  private articles: Article[];
  idArticle:number;
  constructor(private articleService: ArticleService,private route: ActivatedRoute,private router:Router){ }

  ngOnInit() {


  	this.articleService.getListeArticle().then(articles => {this.articles = articles});

 
  }

  DetailArticle(article: Article): void{
        
        this.idArticle=article.articleID;

      let navigationExtras: NavigationExtras= {
          queryParams: {
            "articleID": article.articleID
          }
      }
      this.router.navigate(['/DetailArticle'],navigationExtras);

 }

}
