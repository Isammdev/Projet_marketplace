import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailVendeurComponent } from './detail-vendeur.component';

describe('DetailVendeurComponent', () => {
  let component: DetailVendeurComponent;
  let fixture: ComponentFixture<DetailVendeurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetailVendeurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailVendeurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
