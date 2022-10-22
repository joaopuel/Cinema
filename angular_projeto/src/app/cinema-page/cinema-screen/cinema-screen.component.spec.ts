import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CinemaScreenComponent } from './cinema-screen.component';

describe('CinemaScreenComponent', () => {
  let component: CinemaScreenComponent;
  let fixture: ComponentFixture<CinemaScreenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CinemaScreenComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CinemaScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
