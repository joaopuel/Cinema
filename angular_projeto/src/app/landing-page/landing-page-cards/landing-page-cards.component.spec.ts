import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LandingPageCardsComponent } from './landing-page-cards.component';

describe('LandingPageCardsComponent', () => {
  let component: LandingPageCardsComponent;
  let fixture: ComponentFixture<LandingPageCardsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LandingPageCardsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LandingPageCardsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
