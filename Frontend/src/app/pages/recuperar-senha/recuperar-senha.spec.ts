import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecuperarSenha } from './recuperar-senha';

describe('RecuperarSenha', () => {
  let component: RecuperarSenha;
  let fixture: ComponentFixture<RecuperarSenha>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RecuperarSenha]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RecuperarSenha);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
