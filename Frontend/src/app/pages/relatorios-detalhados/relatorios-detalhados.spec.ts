import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RelatoriosDetalhados } from './relatorios-detalhados';

describe('RelatoriosDetalhados', () => {
  let component: RelatoriosDetalhados;
  let fixture: ComponentFixture<RelatoriosDetalhados>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RelatoriosDetalhados]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RelatoriosDetalhados);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
