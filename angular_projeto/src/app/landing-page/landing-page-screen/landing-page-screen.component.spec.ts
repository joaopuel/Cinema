import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LandingPageScreenComponent } from './landing-page-screen.component';

describe('LandingPageScreenComponent', () => {
  let component: LandingPageScreenComponent;
  let fixture: ComponentFixture<LandingPageScreenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LandingPageScreenComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LandingPageScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
