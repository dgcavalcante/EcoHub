import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdicionarPerfil } from './adicionar-perfil';

describe('AdicionarPerfil', () => {
  let component: AdicionarPerfil;
  let fixture: ComponentFixture<AdicionarPerfil>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdicionarPerfil]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdicionarPerfil);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
