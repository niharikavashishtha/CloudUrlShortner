import { TestBed } from '@angular/core/testing';

import { ClientEntityService } from './client-entity.service';

describe('ClientEntityService', () => {
  let service: ClientEntityService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClientEntityService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
