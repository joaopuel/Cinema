import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CinemasScreenComponent } from './cinemas-screen.component';

describe('CinemasScreenComponent', () => {
  let component: CinemasScreenComponent;
  let fixture: ComponentFixture<CinemasScreenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CinemasScreenComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CinemasScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
