import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FilmeListItemComponent } from './filme-list-item.component';

describe('FilmeListItemComponent', () => {
  let component: FilmeListItemComponent;
  let fixture: ComponentFixture<FilmeListItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FilmeListItemComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FilmeListItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
