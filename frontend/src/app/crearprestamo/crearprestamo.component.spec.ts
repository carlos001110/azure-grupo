import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CrearprestamoComponent } from './crearprestamo.component';

describe('CrearprestamoComponent', () => {
  let component: CrearprestamoComponent;
  let fixture: ComponentFixture<CrearprestamoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CrearprestamoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CrearprestamoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
