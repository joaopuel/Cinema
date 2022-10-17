import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FilmesScreenComponent } from './filmes-screen.component';

describe('FilmesScreenComponent', () => {
  let component: FilmesScreenComponent;
  let fixture: ComponentFixture<FilmesScreenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FilmesScreenComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FilmesScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
