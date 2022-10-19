import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SessoesScreenComponent } from './sessoes-screen.component';

describe('SessoesScreenComponent', () => {
  let component: SessoesScreenComponent;
  let fixture: ComponentFixture<SessoesScreenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SessoesScreenComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SessoesScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
