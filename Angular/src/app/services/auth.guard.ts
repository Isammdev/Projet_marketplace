import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { PersonneService } from './personne.service';

@Injectable()
export class AuthGuard implements CanActivate {
  constructor(private loginService:PersonneService,    private router: Router ) { }

   canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
      this.loginService.checkSession().subscribe(
        res => {
          return true ;

        },
        error => {
          this.router.navigate(['myAccount']);
            return false ;
         }
      );
 
    return true;
  }
}
