import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ControleAparelhos } from './controle-aparelhos';

describe('ControleAparelhos', () => {
  let component: ControleAparelhos;
  let fixture: ComponentFixture<ControleAparelhos>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ControleAparelhos]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ControleAparelhos);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
