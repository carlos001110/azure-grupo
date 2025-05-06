import { TestBed } from '@angular/core/testing';

import { PrestamoRestService } from './prestamo-rest.service';

describe('PrestamoRestService', () => {
  let service: PrestamoRestService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PrestamoRestService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
