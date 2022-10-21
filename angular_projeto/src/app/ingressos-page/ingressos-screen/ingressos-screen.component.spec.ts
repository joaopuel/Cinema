import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IngressosScreenComponent } from './ingressos-screen.component';

describe('IngressosScreenComponent', () => {
  let component: IngressosScreenComponent;
  let fixture: ComponentFixture<IngressosScreenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IngressosScreenComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IngressosScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
