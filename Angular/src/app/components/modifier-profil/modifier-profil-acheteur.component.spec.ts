import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModifierProfilAcheteurComponent } from './modifier-profil-acheteur.component';

describe('ModifierProfilAcheteurComponent', () => {
  let component: ModifierProfilAcheteurComponent;
  let fixture: ComponentFixture<ModifierProfilAcheteurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModifierProfilAcheteurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModifierProfilAcheteurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
