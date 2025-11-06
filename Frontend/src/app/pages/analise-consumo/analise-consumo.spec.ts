import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnaliseConsumo } from './analise-consumo';

describe('AnaliseConsumo', () => {
  let component: AnaliseConsumo;
  let fixture: ComponentFixture<AnaliseConsumo>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AnaliseConsumo]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnaliseConsumo);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
