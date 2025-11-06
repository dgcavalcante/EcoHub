import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MonitoramentoDesperdicio } from './monitoramento-desperdicio';

describe('MonitoramentoDesperdicio', () => {
  let component: MonitoramentoDesperdicio;
  let fixture: ComponentFixture<MonitoramentoDesperdicio>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MonitoramentoDesperdicio]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MonitoramentoDesperdicio);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
