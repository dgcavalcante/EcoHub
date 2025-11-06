import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GerarRelatorios } from './gerar-relatorios';

describe('GerarRelatorios', () => {
  let component: GerarRelatorios;
  let fixture: ComponentFixture<GerarRelatorios>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GerarRelatorios]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GerarRelatorios);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
