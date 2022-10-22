import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CinemaSessoesListItemComponent } from './cinema-sessoes-list-item.component';

describe('CinemaSessoesListItemComponent', () => {
  let component: CinemaSessoesListItemComponent;
  let fixture: ComponentFixture<CinemaSessoesListItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CinemaSessoesListItemComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CinemaSessoesListItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
