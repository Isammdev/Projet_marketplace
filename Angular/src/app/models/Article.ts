import { Personne } from "./Personne";

export class Article {
    articleID: number;
    articleNom: string;
    articleCategorie: string;
    articlePrix:number;
    articleDescription: string;
    photoArticle:string;
    articleNumberDispo:number
    vendeur:Personne;
    
}