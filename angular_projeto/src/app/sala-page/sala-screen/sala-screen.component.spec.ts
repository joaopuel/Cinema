import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SalaScreenComponent } from './sala-screen.component';

describe('SalaScreenComponent', () => {
  let component: SalaScreenComponent;
  let fixture: ComponentFixture<SalaScreenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SalaScreenComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SalaScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
