import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddNewArticleComponent } from './add-new-article.component';

describe('AddNewArticleComponent', () => {
  let component: AddNewArticleComponent;
  let fixture: ComponentFixture<AddNewArticleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddNewArticleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddNewArticleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
