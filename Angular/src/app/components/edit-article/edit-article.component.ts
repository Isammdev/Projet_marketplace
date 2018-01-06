import { Component, OnInit } from '@angular/core';
//import { UploadImageService } from '../../services/upload-image.service';
import { Article } from '../../models/article';
import { Params, ActivatedRoute, Router } from '@angular/router';
import { ArticleService } from '../../services/article.service';
 import { UploadFileService } from '../../services/upload-file.service';
import { HttpClient, HttpResponse, HttpEventType } from '@angular/common/http';
 import { PersonneService } from '../../services/personne.service';

@Component({
  selector: 'app-edit-article',
  templateUrl: './edit-article.component.html',
  styleUrls: ['./edit-article.component.css']
})
export class EditArticleComponent implements OnInit {

  private articleId: number;
  private newArticle: Article = new Article();
  private articleUpdated: boolean;
  private selectedFiles: FileList;
  private currentFileUpload: File;
  private  progress: { percentage: number } = { percentage: 0};
  constructor(
    private loginService:PersonneService,  
  	//private uploadImageService: UploadImageService,
  	private editArticleService: ArticleService,
   	private route: ActivatedRoute,
	  private router: Router,
	   private uploadService: UploadFileService
  	) { }

  onSubmit() {
  	this.editArticleService.updateArticle(this.newArticle).subscribe(
  		data => {
 			this.articleUpdated=true;
  		},
  		error => console.log(error)
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

  upload() {
    

    this.currentFileUpload = this.selectedFiles.item(0)
    this.uploadService.pushFileToStorage(this.currentFileUpload,this.articleId ).subscribe(event => {
      if (event.type === HttpEventType.UploadProgress) {
        this.progress.percentage = Math.round(100 * event.loaded / event.total);
      } else if (event instanceof HttpResponse) {
        console.log('File is completely uploaded!');
      }
    })

   
  }
  ngOnInit() {
  
  	this.route.params.forEach((params: Params) => {
  		this.articleId = Number.parseInt(params['id']);
  	});

  	this.editArticleService.getArticle(this.articleId).subscribe(
  		res => {
  			this.newArticle = res.json();
  		}, 
  		error => console.log(error)
  	)
  }

}
