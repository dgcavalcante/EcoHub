import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Router } from '@angular/router'; // Necessário para testar a navegação
import { RouterTestingModule } from '@angular/router/testing'; // Módulo de teste para Router
import { FormsModule } from '@angular/forms'; // Necessário para testar bindings de formulário

import { AdicionarAparelhoComponent } from './adicionar-aparelho'; 

describe('AdicionarAparelhoComponent', () => { 
  let component: AdicionarAparelhoComponent; 
  let fixture: ComponentFixture<AdicionarAparelhoComponent>; 
  let router: Router;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      // Como é um componente standalone, importamos ele e suas dependências necessárias
      imports: [AdicionarAparelhoComponent, RouterTestingModule.withRoutes([])],
      providers: [
        // Opcional: mock do Router se não usar RouterTestingModule.withRoutes
        // { provide: Router, useValue: { navigate: jasmine.createSpy('navigate') } } 
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdicionarAparelhoComponent); 
    component = fixture.componentInstance;
    router = TestBed.inject(Router);
    fixture.detectChanges();
  });

  it('should create the component', () => {
    // Verifica se o componente foi criado corretamente
    expect(component).toBeTruthy();
  });

  it('should call saveDevice and navigate to control page', () => {
    // Espia (mock) o método de navegação do router
    const navigateSpy = spyOn(router, 'navigate');

    // Define dados de teste para passar na validação básica de ngModel
    component.newDevice.name = 'Lâmpada Teste';
    
    // Chama a função de salvar
    component.saveDevice();
    
    // Verifica se a navegação foi chamada com a rota correta após salvar (simulação)
    expect(navigateSpy).toHaveBeenCalledWith(['/controle-aparelhos']);
  });
});