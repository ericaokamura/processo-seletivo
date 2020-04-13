import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FiltroLogComponent } from './filtro-log.component';

describe('FiltroLogComponent', () => {
  let component: FiltroLogComponent;
  let fixture: ComponentFixture<FiltroLogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FiltroLogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FiltroLogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
