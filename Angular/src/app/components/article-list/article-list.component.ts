import { Component, OnInit } from '@angular/core';
import {Article} from '../../models/article';
import {Router} from '@angular/router';
import {ArticleService} from '../../services/article.service';
 
//import {MatDialog, MatDialogRef} from '@angular/material';

@Component({
  selector: 'app-article-list',
  templateUrl: './article-list.component.html',
  styleUrls: ['./article-list.component.css']
})
export class ArticleListComponent implements OnInit {
	private selectedArticle : Article;
 	private articleList: Article[];
  
  constructor(
    private getArticleListService: ArticleService,
    private removeArticleService: ArticleService,
    private router:Router,
   // public dialog:MatDialog
    ) { }

  onSelect(article:Article) {
    this.selectedArticle=article;
    this.router.navigate(['/editArticle', this.selectedArticle.articleID]);
  }
  Supprimer(article :Article):void{
    this.removeArticleService.supprimerArticle(article.articleID).subscribe(response =>
                    {
                      this.getArticleList();
     
    
                    },
                    error =>
                    {
                        console.log(error);
                    });
    
    
    }
  
  
 

  getArticleList() {
    this.getArticleListService.getArticleList().subscribe(
      res => {
        console.log(res.json());
        this.articleList=res.json();
      }, 
      error => {
        console.log(error);
      }
      );
  }

  ngOnInit() {
  	this.getArticleList();
  }

}
 