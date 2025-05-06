import { Prestamo } from './prestamo';

describe('Prestamo', () => {
  it('should create an instance', () => {
    const prestamo = new Prestamo(
      1,                       // id
      101,                     // itemId
      'Juan Perez',            // persona
      new Date('2025-03-28'),  // fechaPrestamo
      new Date('2025-04-10'),  // fechaPrevistaDevolucion
      new Date('2025-04-05'),  // fechaDevolucion
      true                      // activo
    );
    expect(prestamo).toBeTruthy();
  });
});
