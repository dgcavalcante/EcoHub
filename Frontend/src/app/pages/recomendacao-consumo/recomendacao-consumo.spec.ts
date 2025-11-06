import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecomendacaoConsumo } from './recomendacao-consumo';

describe('RecomendacaoConsumo', () => {
  let component: RecomendacaoConsumo;
  let fixture: ComponentFixture<RecomendacaoConsumo>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RecomendacaoConsumo]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RecomendacaoConsumo);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
