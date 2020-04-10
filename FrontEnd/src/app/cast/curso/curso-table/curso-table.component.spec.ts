import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CursoTableComponent } from './curso-table.component';

describe('CursoTableComponent', () => {
  let component: CursoTableComponent;
  let fixture: ComponentFixture<CursoTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CursoTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CursoTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
