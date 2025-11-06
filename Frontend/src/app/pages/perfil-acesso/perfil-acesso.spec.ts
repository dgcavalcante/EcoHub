import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PerfilAcesso } from './perfil-acesso';

describe('PerfilAcesso', () => {
  let component: PerfilAcesso;
  let fixture: ComponentFixture<PerfilAcesso>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PerfilAcesso]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PerfilAcesso);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
