import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListeFavoriComponent } from './liste-favori.component';

describe('ListeFavoriComponent', () => {
  let component: ListeFavoriComponent;
  let fixture: ComponentFixture<ListeFavoriComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListeFavoriComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListeFavoriComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
