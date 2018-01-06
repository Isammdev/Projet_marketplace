import { Personne } from './personne';
import { Article } from './article';
export class Panier {
	public id: number;
	public acheteur: Personne;
	public article: Article;
	public dateAchat:string;
	public nombreArticle:number;
}
