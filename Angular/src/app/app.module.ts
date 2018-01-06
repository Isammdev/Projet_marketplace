import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {RouterModule,Routes} from '@angular/router';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import {MatGridListModule, MatRadioModule,MatSlideToggleModule, MatToolbarModule , MatSidenavModule , MatButtonModule, MatIconModule } from '@angular/material';
import 'hammerjs';
import {MatTabsModule} from '@angular/material/tabs';
import { PersonneService } from './services/personne.service';
import { ArticleService} from './services/article.service';
import { ListeArticleComponent } from './components/liste-article/liste-article.component';
import { HomeComponent } from './components/home/home.component';
import { CompteComponent } from './components/compte/compte.component';
import { AppComponent } from './app.component';
 import { ChercherArticleComponent } from './components/chercher-article/chercher-article.component';
 import { MyAccountComponent } from './components/my-account/my-account.component';
 import { AddNewArticleComponent } from './components/add-new-article/add-new-article.component';
 import { ArticleListComponent } from './components/article-list/article-list.component';
 import { ViewArticleComponent } from './components/view-article/view-article.component';
 import { EditArticleComponent } from './components/edit-article/edit-article.component';
import { UploadFileService } from './services/upload-file.service';
import { HttpClientModule } from '@angular/common/http';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
 import { ModifierProfilAcheteurComponent } from './components/modifier-profil/modifier-profil-acheteur.component';
 import { DetailVendeurComponent } from './components/detail-vendeur/detail-vendeur.component';
 import { ListeFavoriComponent  } from './components/liste-favori/liste-favori.component';
 import {   DetailArticleComponent } from './components/detail-article/detail-article.component';
import { ListePanierComponent } from './components/liste-panier/liste-panier.component';
import { AuthGuard } from './services/auth.guard';
import { RechercheAvanceComponent } from './components/recherche-avance/recherche-avance.component';
import { Ng2FilterPipeModule } from 'ng2-filter-pipe';
import { MessageComponent } from './components/message/message.component';
 
 
 
const appRoutes: Routes = [
  {path:"", component:HomeComponent},
  {path:"home", component:HomeComponent},
  {path:"compte", component:CompteComponent},
  {path:"ListeArticle", component:ListeArticleComponent},
  {path:"DetailArticle", component:DetailArticleComponent},
  {path:"ChercherAngrticle", component:ChercherArticleComponent}  ,
  {path:"myArticles", component:ChercherArticleComponent,
  canActivate: [AuthGuard]}  ,
  {path:"recherche", component:RechercheAvanceComponent}  ,
  {path:"message/:email", component:MessageComponent,
  canActivate: [AuthGuard]}  ,
  {
		path: 'addNewArticle',
    component: AddNewArticleComponent,
    canActivate: [AuthGuard]
	},
	{
		path: 'articleList',
		component: ArticleListComponent
	},
	{
		path: 'viewArticle/:id',
		component: ViewArticleComponent
	},
	{
		path: 'editArticle/:id',
    component: EditArticleComponent,
    canActivate: [AuthGuard]
	}
  ,
  {
		path: 'myAccount',
    component: MyAccountComponent,
  }
 , {path:"ModifProfilAcheteur", component:ModifierProfilAcheteurComponent,    canActivate: [AuthGuard]}
,
 {
  path: 'DetailVendeur',
  component: DetailVendeurComponent
},
{
  path: 'ListeFavoris',
  component: ListeFavoriComponent,    canActivate: [AuthGuard]
},
{path:"ListePanier", component:ListePanierComponent,    canActivate: [AuthGuard]}

];
@NgModule({
  declarations: [
    AppComponent,  DetailArticleComponent,ArticleListComponent,ListePanierComponent,
    ChercherArticleComponent,AddNewArticleComponent,ViewArticleComponent,RechercheAvanceComponent,
    MyAccountComponent ,EditArticleComponent,ListeFavoriComponent,
   CompteComponent,HomeComponent, DetailVendeurComponent, ModifierProfilAcheteurComponent , ListeArticleComponent,NavBarComponent, MessageComponent
   
    ],
  imports: [
    BrowserModule,    HttpModule,HttpClientModule,
    Ng2FilterPipeModule,
    BrowserAnimationsModule,
    RouterModule.forRoot(appRoutes),
    FormsModule,
    HttpModule, 
    MatGridListModule,
    MatRadioModule, MatTabsModule,MatSlideToggleModule, MatToolbarModule , MatSidenavModule , MatButtonModule, MatIconModule ,
   ],
  providers: [AuthGuard,
 UploadFileService,
   PersonneService,ArticleService
   
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
