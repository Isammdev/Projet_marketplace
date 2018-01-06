import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {ActivatedRoute, NavigationExtras} from "@angular/router";
import { ArticleService } from '../../services/article.service';
import { Article } from '../../models/Article';
 
@Component({
  selector: 'app-detail-article',
  templateUrl: './detail-article.component.html',
  styleUrls: ['./detail-article.component.css'],
  providers: [ArticleService]
})
export class DetailArticleComponent implements OnInit {
offreID:number;
article:Article;
orderby:string;
msg:string;
panier:Boolean;
comm:string;
  constructor(private articleService: ArticleService,private route: ActivatedRoute,private router:Router) { }

  ngOnInit() {
  	this.route.queryParams.subscribe(params =>{
	  this.offreID= params["articleID"];
	  })

this.articleService.getDetailArticle(this.offreID).then(article=> this.article = article);


  	
  }



AjoutFavori(article:Article):void{



  this.articleService.ajoutFavoriArticle(article.articleID).then(response =>
                {
                    console.log(response);
                },
                error =>
                {
                    console.log(error);
                });
}



DetailArticle(article:Article):void{

  let navigationExtras: NavigationExtras= {
          queryParams: {
            "vendeurID": article.vendeur.personneID
          }
      }

 this.router.navigate(['/DetailVendeur'],navigationExtras);}


AjouterPanier():void{
this.panier=true;



}

Valider():void{


  if(this.article.articleNumberDispo<Number(this.orderby))
  {
    this.msg="ok";


  }
  else if(this.article.articleNumberDispo-Number(this.orderby)<0){
     this.msg="ok";
  }

  else{
this.articleService.ajoutPanierArticle(this.article.articleID,Number(this.orderby)).then(response =>
                {
                    console.log(response);
                },
                error =>
                {
                    console.log(error);
                });




  }
}


ajoutComm(article: Article):void
{

this.articleService.ajoutCommArticle(article.articleID,this.comm).then(response =>
                {
                    console.log(response);
                },
                error =>
                {
                    console.log(error);
                });

}

}
