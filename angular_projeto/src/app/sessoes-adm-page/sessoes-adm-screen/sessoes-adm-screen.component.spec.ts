import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SessoesAdmScreenComponent } from './sessoes-adm-screen.component';

describe('SessoesAdmScreenComponent', () => {
  let component: SessoesAdmScreenComponent;
  let fixture: ComponentFixture<SessoesAdmScreenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SessoesAdmScreenComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SessoesAdmScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
