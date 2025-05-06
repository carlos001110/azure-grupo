import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModificaritemComponent } from './modificaritem.component';

describe('ModificaritemComponent', () => {
  let component: ModificaritemComponent;
  let fixture: ComponentFixture<ModificaritemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModificaritemComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModificaritemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
