import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreartipoComponent } from './creartipo.component';

describe('CreartipoComponent', () => {
  let component: CreartipoComponent;
  let fixture: ComponentFixture<CreartipoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreartipoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreartipoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
