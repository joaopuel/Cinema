import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SessoesListItemComponent } from './sessoes-list-item.component';

describe('SessoesListItemComponent', () => {
  let component: SessoesListItemComponent;
  let fixture: ComponentFixture<SessoesListItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SessoesListItemComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SessoesListItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
