import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SessaoScreenComponent } from './sessao-screen.component';

describe('SessaoScreenComponent', () => {
  let component: SessaoScreenComponent;
  let fixture: ComponentFixture<SessaoScreenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SessaoScreenComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SessaoScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
