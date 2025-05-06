import { TestBed } from '@angular/core/testing';

import { TipoRestService } from './tipo-rest.service';

describe('TipoRestService', () => {
  let service: TipoRestService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TipoRestService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
