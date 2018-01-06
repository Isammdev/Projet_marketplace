import { Component, OnInit } from '@angular/core';
import { Article } from '../../models/article';
import { ArticleService } from '../../services/article.service';
 
import { HttpClient, HttpResponse, HttpEventType } from '@angular/common/http';

import { ActivatedRoute, Router } from '@angular/router';
 import { UploadFileService } from '../../services/upload-file.service';
import { PersonneService } from '../../services/personne.service';

@Component({
  selector: 'app-add-new-article',
  templateUrl: './add-new-article.component.html',
  styleUrls: ['./add-new-article.component.css']
})
export class AddNewArticleComponent implements OnInit {


  private newArticle: Article = new Article();
  private articleAdded: boolean;
  private selectedFiles: FileList;
  private currentFileUpload: File;
  private progress: { percentage: number } = { percentage: 0 }
  
  image: string;
  constructor(  private router:Router ,private addArticleService: ArticleService, private uploadService: UploadFileService,private loginService: PersonneService) { }

  onSubmit() {
    this.addArticleService.sendArticle(this.newArticle).subscribe(
      res => {
        this.upload(JSON.parse(JSON.parse(JSON.stringify(res))._body).articleID);
        this.articleAdded = true;
        this.newArticle = new Article();

      },
      error => {
        console.log(error);
      }
    );
  }
  selectFile(event) {
    const file = event.target.files.item(0)

    if (file.type.match('image.*')) {
      this.selectedFiles = event.target.files;
    } else {
      alert('invalid format!');
    }
  }

  upload(id: number) {


    this.currentFileUpload = this.selectedFiles.item(0)
    this.uploadService.pushFileToStorage(this.currentFileUpload, id).subscribe(event => {
      if (event.type === HttpEventType.UploadProgress) {
        this.progress.percentage = Math.round(100 * event.loaded / event.total);
      } else if (event instanceof HttpResponse) {
        console.log('File is completely uploaded!');
      }
    })


  }
  ngOnInit() {
    this.articleAdded = false;
    
   
  }

}
