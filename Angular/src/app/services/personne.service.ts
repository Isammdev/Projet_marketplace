import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import { Personne } from '../models/Personne';  

 
@Injectable()
export class PersonneService {
  private apiUrl = 'http://localhost:8080/personne/getPersonne';
  private apiUrldetail = 'http://localhost:8080/personne/getPersonneByID';

 private serverPath: string = 'http://localhost:8080/personne';
  private serverLogin: string = 'http://localhost:8080/login';
  private apiUrlUpdate = 'http://localhost:8080/personne/UpdatePersonne';
private apiUrlUpdatePass = 'http://localhost:8080/personne/updatePassword';
private apiUrlUpdateBoutique = 'http://localhost:8080/personne/updateBoutique';

  constructor(private http:Http) { }

  ajoutPersonne(username: string, email:string, role:string, boutiqueAdresse:string , boutiqueNom:string) {
  	let url = this.serverPath+'/ajoutPersonne';
  	let userInfo = {
  		"username" : username,
      "email" : email,
      "role"  : role ,
      "boutiqueAdresse": boutiqueAdresse,
      "boutiqueNom" : boutiqueNom
  	}
  	let tokenHeader = new Headers({
  		'Content-Type' : 'application/json',
  		'x-auth-token' : localStorage.getItem('xAuthToken')
  	});

  	return this.http.post(url, JSON.stringify(userInfo), {headers : tokenHeader});
  }

  message( object :string, titre :string , Email :string) {
  	let url = this.serverPath+'/messge';
  	let userInfo = {
  		"object" : object,
      "titre" : titre,
      "Email"  : Email 
     
  	}
  	let tokenHeader = new Headers({
  		'Content-Type' : 'application/json',
  		'x-auth-token' : localStorage.getItem('xAuthToken')
  	});

  	return this.http.post(url, JSON.stringify(userInfo), {headers : tokenHeader});
  }

  ajoutPersonne2(username: string, email:string ) {
  	let url = this.serverPath+'/ajoutPersonne';
  	let userInfo = {
  		"username" : username,
      "email" : email,
       
  	}
  	let tokenHeader = new Headers({
  		'Content-Type' : 'application/json',
  		'x-auth-token' : localStorage.getItem('xAuthToken')
  	});

  	return this.http.post(url, JSON.stringify(userInfo), {headers : tokenHeader});
  }

login(email: string, password: string) {
    let url = this.serverLogin+'/token';
    let encodedCredentials = btoa(email+":"+password);
    let basicHeader = "Basic "+encodedCredentials;
    let headers = new Headers({
      'Content-Type' : 'application/x-www-form-urlencoded',
      'Authorization' : basicHeader
    });

    return this.http.get(url, {headers: headers});
  }
  checkSession() {
  	let url = this.serverLogin+'/checkSession';
  	let headers = new Headers({
  		'x-auth-token' : localStorage.getItem('xAuthToken')
  	});

  	return this.http.get(url, {headers: headers});
  }

   

  logout() {
  	let url = this.serverLogin+'/user/logout';
  	let headers = new Headers({
  		'x-auth-token' : localStorage.getItem('xAuthToken')
  	});

  	return this.http.post(url, '', {headers: headers});
  }
  updateUserInfo(personne: Personne, newPassword: string, currentPassword: string) {
    let url = this.serverPath + "/updateUserInfo";
    let userInfo = {
      "id" : personne.personneID,
      "firstName": personne.personnePrenom,
      "lastName": personne.personneNom,
       "currentPassword" : currentPassword,
      "email" : personne.personneMail,
      "newPassword" :newPassword
    };

    let tokenHeader = new Headers({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem("xAuthToken")
    });
    return this.http.post(url, JSON.stringify(userInfo), {headers:tokenHeader});
  }

  retrievePassword(email:string) {
  	let url = this.serverPath+'/forgetPassword';
  	let userInfo = {
  		"email" : email
  	}
  	let tokenHeader = new Headers({
  		'Content-Type' : 'application/json',
  		'x-auth-token' : localStorage.getItem('xAuthToken')
  	});

  	return this.http.post(url, JSON.stringify(userInfo), {headers : tokenHeader});
  }

  updatePersonne(nom:string,prenom:string,mobile:string,adresse:string):Promise<Personne>{

  let tokenHeader = new Headers({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    const urlWithId = this.apiUrlUpdate +'/'+nom+'/'+prenom+'/'+mobile+'/'+adresse;

        return this.http
            .get(urlWithId,{headers : tokenHeader})
            .toPromise()
        .then(response => response.json() as Personne)
        .catch(this.handleError);
  }



updatePassword(password:string):Promise<Personne>{

  let tokenHeader = new Headers({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

const urlWithId = this.apiUrlUpdatePass +'/'+password;

return this.http
            .get(urlWithId,{headers : tokenHeader})
            .toPromise()
        .then(response => response.json() as Personne)
        .catch(this.handleError);

}

updateboutique(nom:string,  adresse:string):Promise<Personne>{

  let tokenHeader = new Headers({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

const urlWithId2 = this.apiUrlUpdateBoutique +'/'+nom +'/'+adresse;

return this.http
            .get(urlWithId2,{headers : tokenHeader})
            .toPromise()
        .then(response => response.json() as Personne)
        .catch(this.handleError);

}

private handleError(error: any): Promise<any> {
	    console.error('An error occurred', error);
	    return Promise.reject(error.message || error);
  	}

    getPersonne():Promise<Personne>
    {

    	let tokenHeader = new Headers({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });
        
	

       return this.http.get(this.apiUrl,{headers : tokenHeader})
	      .toPromise()
	      .then(response => response.json() as Personne)
	      .catch(this.handleError);
    }
  getCurrentUser() {
    let url = this.serverPath+'/getCurrentUser';
    
    let tokenHeader = new Headers({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    return this.http.get(url, {headers : tokenHeader});
  }

  getVendeur(vendeurID:number):Promise<Personne>{
    const url = `${this.apiUrldetail}/${vendeurID}`;
    
           return this.http.get(url)
            .toPromise()
            .then(response => response.json() as Personne )
            .catch(this.handleError);
    
    
    }
  
}
