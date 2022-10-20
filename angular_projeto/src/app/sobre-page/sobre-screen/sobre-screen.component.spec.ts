import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SobreScreenComponent } from './sobre-screen.component';

describe('SobreScreenComponent', () => {
  let component: SobreScreenComponent;
  let fixture: ComponentFixture<SobreScreenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SobreScreenComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SobreScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
