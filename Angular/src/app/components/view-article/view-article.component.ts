import { Component, OnInit } from '@angular/core';
import {Params, ActivatedRoute, Router} from '@angular/router';
import {ArticleService} from '../../services/article.service';
import {Article} from '../../models/article';

@Component({
  selector: 'app-view-article',
  templateUrl: './view-article.component.html',
  styleUrls: ['./view-article.component.css']
})
export class ViewArticleComponent implements OnInit {

  private article:Article = new Article();
  private articleId: number;

  constructor(private getArticleService:ArticleService,
  	private route:ActivatedRoute, private router:Router) { }

  onSelect(article:Article) {
    this.router.navigate(['/editArticle', this.article.articleID])
    // .then(s => location.reload())
    ;
  }

  ngOnInit() {
  	this.route.params.forEach((params: Params) => {
  		this.articleId = Number.parseInt(params['id']);
  	});

  	this.getArticleService.getArticle(this.articleId).subscribe(
  		res => {
  			this.article = res.json();
  		},
  		error => {
  			console.log(error);
  		}
  	);

  	
  }

}
