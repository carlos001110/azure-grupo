import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormularioitemComponent } from './formularioitem.component';

describe('FormularioitemComponent', () => {
  let component: FormularioitemComponent;
  let fixture: ComponentFixture<FormularioitemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormularioitemComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormularioitemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
