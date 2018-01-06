import { Component, OnInit } from '@angular/core';
import { PersonneService } from '../../services/personne.service';
import { ActivatedRoute, Router, Params } from '@angular/router';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit {
  messageok: boolean;
  email:string ;
  object:string ;

  titre:string ;

  constructor(private articleService: PersonneService,private route: ActivatedRoute,private router:Router) { }

  ngOnInit() {

this.object = null ;
this.titre = null ;

  	this.route.params.forEach((params: Params) => {
  		this.email =  params['email'] ;
  	});
    
    
  }
  onSubmit() {
  	this.articleService.message(this.object,this.titre,this.email).subscribe(
  		data => {
       this.messageok=true;
       this.object = null ;
this.titre = null ;
  		},
  		error => console.log(error)
  	);
  }
}
