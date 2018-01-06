import { Component, OnInit } from '@angular/core';
 
import { HttpClient, HttpResponse, HttpEventType } from '@angular/common/http';
import { Router } from '@angular/router';
import { PersonneService } from '../../services/personne.service';
import { Personne } from '../../models/Personne';
import { UploadFileService } from '../../services/upload-file.service';


@Component({
  selector: 'app-modifier-profil-acheteur',
  templateUrl: './modifier-profil-acheteur.component.html',
  styleUrls: ['./modifier-profil-acheteur.component.css'],
  providers: [PersonneService,UploadFileService,HttpClient]
})
export class ModifierProfilAcheteurComponent implements OnInit {
  private personne: Personne = new Personne();
  currentFileUpload: File;
  image:string;
  progress: { percentage: number } = { percentage: 0 }
  selectedFiles: FileList;
  pass: string;
  pass1: string;
  Adresseb: string;
  Nomb: string;
  msg:boolean;
  Isvendeur:boolean=false ;
  constructor(private personneService: PersonneService,private uploadService: UploadFileService,private router:Router) { }



  selectFile(event) {
    const file = event.target.files.item(0)

    if (file.type.match('image.*')) {
      this.selectedFiles = event.target.files;
    } else {
      alert('invalid format!');
    }
  }



  ngOnInit() {
    this.Isvendeur= false ;

        this.personneService.getPersonne().then(personne => {this.personne = personne ;
          this.Nomb= this.personne.boutique.boutiqueNom;
          this.Adresseb= this.personne.boutique.boutiqueAdresse;
        }
        );
    

        if (this.personne.personneRole =="Acheteur")
        this.Isvendeur=false;
        else
        this.Isvendeur=true;
        
  }




updateProfil(personne:Personne):void{

this.personneService.updatePersonne(personne.personneNom,personne.personnePrenom,personne.personneMobile,personne.personneAdresse).then(response =>
                {
                    console.log(response);
                },
                error =>
                {
                    console.log(error);
                });

}




upload() {
    

    this.currentFileUpload = this.selectedFiles.item(0)
    this.uploadService.ModifPic(this.currentFileUpload,this.personne.personneID).subscribe(event => {
      if (event.type === HttpEventType.UploadProgress) {
        this.progress.percentage = Math.round(100 * event.loaded / event.total);
      } else if (event instanceof HttpResponse) {
        console.log('File is completely uploaded!');
      }
    })

    this.selectedFiles = undefined

this.router.navigate(['/ModifProfilAcheteur']);
  }


updatePasword():void
{if(this.pass!=this.pass1)
  {


    this.msg=true;
  }
  else{


this.personneService.updatePassword(this.pass).then(response =>
                {
                    console.log(response);
                },
                error =>
                {
                    console.log(error);
                });

  }



}


updateBoutique():void
{

this.personneService.updateboutique(this.Nomb,this. Adresseb).then(response =>
                {
                    console.log(response);
                },
                error =>
                {
                    console.log(error);
                });

  }



}


 
