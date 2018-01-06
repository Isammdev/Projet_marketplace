import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
 import 'rxjs/add/operator/toPromise';
import {Observable} from 'rxjs/Rx';
import { Article } from '../models/Article';
import { Favori } from '../models/favori';
import { Panier } from '../models/panier';
import { Commande } from '../models/commande';
@Injectable()
export class ArticleService {

	private apiUrlfav = 'http://localhost:8080/article/getFavori';
	private apiUrlSupp = 'http://localhost:8080/article/SupprimerFavori';
private apiUrl = 'http://localhost:8080/article';
private apicherche = 'http://localhost:8080/article/getArticleFiltre';
private apiUrlGetpanier = 'http://localhost:8080/article/getPanier';
private apiUrlSupppanier = 'http://localhost:8080/article/SupprimerPanier';
constructor(private http: Http) {
 
  }
 	getListeArticle(): Promise<Article[]> {
	    const url = `${this.apiUrl}/allArticle`
	    return this.http.get(url)
	      .toPromise()
	      .then(response => response.json() as Article[])
	      .catch(this.handleError);
	  }	

	  sendArticle(article:Article) {
		let url = "http://localhost:8080/vendeur/addArticle";
	  
	  let headers = new Headers ({
		'Content-Type': 'application/json',
		'x-auth-token' : localStorage.getItem('xAuthToken')
	  });
  
	  return this.http.post(url, JSON.stringify(article), {headers: headers});
	}
	getArticleList() {
  	let url = "http://localhost:8080/vendeur/getMyArticles";
    let headers = new Headers ({
      'Content-Type': 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    return this.http.get(url, {headers: headers});
  }

	
	updateArticle(article:Article) {
		let url = "http://localhost:8080/vendeur/updateArticle" +'/'+ article.articleID +'/'+article.articleNom  +'/' + article.articleCategorie + '/'+article.articleDescription +'/' + article.articlePrix +'/' + article.articleNumberDispo  ;
	  
	  let headers = new Headers ({
		'Content-Type': 'application/json',
		'x-auth-token' : localStorage.getItem('xAuthToken')
	  });
  
	  return this.http.get(url, {headers: headers});
	}

	supprimerArticle(articleId: number) {
		let url = "http://localhost:8080/vendeur/remove";
	  
	  let headers = new Headers ({
		'Content-Type': 'application/json',
		'x-auth-token' : localStorage.getItem('xAuthToken')
	  });
  
	  return this.http.post(url, articleId, {headers: headers});
	}

private handleError(error: any): Promise<any> {
	    console.error('An error occurred', error);
	    return Promise.reject(error.message || error);
	  }
	  
	  getArticlesFiltres(categorie:string):Promise<Article[]>
	  {
		  
	  const url = `${this.apicherche}/${categorie}`;
  
		 return this.http.get(url)
			.toPromise()
			.then(response => response.json() as Article[])
			.catch(this.handleError);
	  }
	  getDetailArticle(articleID: number): Promise<Article> {
	    const url = `${this.apiUrl}/getArticleID/${articleID}`;
	    return this.http.get(url)
	      .toPromise()
	      .then(response => response.json() as Article)
	      .catch(this.handleError);
	  }	
	  getArticle(id:number) {
		let url = "http://localhost:8080/article/getArticleID/"+id;
	  let headers = new Headers ({
		'Content-Type': 'application/json',
		'x-auth-token' : localStorage.getItem('xAuthToken')
	  });
  
	  return this.http.get(url, {headers: headers});
	}

	ajoutFavoriArticle(articleID: number): Promise<Article> {

		let tokenHeader = new Headers({
				'Content-Type' : 'application/json',
				'x-auth-token' : localStorage.getItem('xAuthToken')
			});
					
				const url = `${this.apiUrl}/favoriArticle/${articleID}`;
				return this.http.get(url,{headers : tokenHeader})
					.toPromise()
					.then(response => response.json())
					.catch(this.handleError);
			}	
	
			getFavori():Promise<Favori[]>
			{
	
	
	
	
	
					let tokenHeader = new Headers({
				'Content-Type' : 'application/json',
				'x-auth-token' : localStorage.getItem('xAuthToken')
			});
		const url = `${this.apiUrlfav}`;
	
				 return this.http.get(url,{headers : tokenHeader})
					.toPromise()
					.then(response => response.json() as Favori[])
					.catch(this.handleError);
			}
	
	SupprimerFavori(articleID:number){
	const url = `${this.apiUrlSupp}/${articleID}`;
	
				 return this.http.get(url)
					.toPromise()
					.then(response => response.json() )
					.catch(this.handleError);
	
	
	}
	ajoutPanierArticle(articleID: number,qte:number): Promise<Article> {

		let tokenHeader = new Headers({
				'Content-Type' : 'application/json',
				'x-auth-token' : localStorage.getItem('xAuthToken')
			});
					
				const url = `${this.apiUrl}/panierArticle/${articleID}/${qte}`;
				return this.http.get(url,{headers : tokenHeader})
					.toPromise()
					.then(response => response.json())
					.catch(this.handleError);
			}	
	
			getPanier():Promise<Panier[]>
			{
	
	
	
	
	
					let tokenHeader = new Headers({
				'Content-Type' : 'application/json',
				'x-auth-token' : localStorage.getItem('xAuthToken')
			});
		const url = `${this.apiUrlGetpanier}`;
	
				 return this.http.get(url,{headers : tokenHeader})
					.toPromise()
					.then(response => response.json() as Panier[])
					.catch(this.handleError);
			}
	
	SupprimerPanier(articleID:number){
	const url = `${this.apiUrlSupppanier}/${articleID}`;
	
				 return this.http.get(url)
					.toPromise()
					.then(response => response.json() )
					.catch(this.handleError);
	
	
	}
			
 
		payer(commande:Commande,sum:number) {
			let urlpayment = "http://localhost:8080/personne/payer/"+sum;
			
			let headers = new Headers ({
			'Content-Type': 'application/json',
			'x-auth-token' : localStorage.getItem('xAuthToken')
			});
		
			return this.http.post(urlpayment, JSON.stringify(commande), {headers: headers});
		}
		ajoutCommArticle(articleID: number,comm:string): Promise<Article> {

			let tokenHeader = new Headers({
					'Content-Type' : 'application/json',
					'x-auth-token' : localStorage.getItem('xAuthToken')
				});
						
					const url = `${this.apiUrl}/ajoutCommentaire/${articleID}/${comm}`;
					return this.http.get(url,{headers : tokenHeader})
						.toPromise()
						.then(response => response.json())
						.catch(this.handleError);
				}	
		
	}