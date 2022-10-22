import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SalasScreenComponent } from './salas-screen.component';

describe('SalasScreenComponent', () => {
  let component: SalasScreenComponent;
  let fixture: ComponentFixture<SalasScreenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SalasScreenComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SalasScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
