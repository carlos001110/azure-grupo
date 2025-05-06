import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EliminarItemComponent } from './eliminarItem.component';

describe('ListaitemsComponent', () => {
  let component: EliminarItemComponent;
  let fixture: ComponentFixture<EliminarItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EliminarItemComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EliminarItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
