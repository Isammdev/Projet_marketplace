import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChercherArticleComponent } from './chercher-article.component';

describe('ChercherArticleComponent', () => {
  let component: ChercherArticleComponent;
  let fixture: ComponentFixture<ChercherArticleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChercherArticleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChercherArticleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
