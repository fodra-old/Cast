import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CursoModalComponent } from './curso-modal.component';

describe('CursoModalComponent', () => {
  let component: CursoModalComponent;
  let fixture: ComponentFixture<CursoModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CursoModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CursoModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
