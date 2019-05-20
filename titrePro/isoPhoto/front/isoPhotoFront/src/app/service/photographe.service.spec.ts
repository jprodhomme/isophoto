import { TestBed } from '@angular/core/testing';

import { PhotographeService } from './photographe.service';

describe('PhotographeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PhotographeService = TestBed.get(PhotographeService);
    expect(service).toBeTruthy();
  });
});
