import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FilmeInfoScreenComponent } from './filme-info-screen.component';

describe('FilmeInfoScreenComponent', () => {
  let component: FilmeInfoScreenComponent;
  let fixture: ComponentFixture<FilmeInfoScreenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FilmeInfoScreenComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FilmeInfoScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
