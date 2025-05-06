import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ListatipoComponent } from './listatipo.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TipoRestService } from '../tipo-rest.service';

describe('ListatipoComponent', () => {
  let component: ListatipoComponent;
  let fixture: ComponentFixture<ListatipoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [ListatipoComponent],
      providers: [TipoRestService]
    }).compileComponents();

    fixture = TestBed.createComponent(ListatipoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('debería crearse correctamente', () => {
    expect(component).toBeTruthy();
  });

  it('debería inicializar con lista vacía de tipos', () => {
    expect(component.tipos.length).toBe(0);
  });
});
