import { TestBed } from '@angular/core/testing';

import { MazoService } from './mazo.service';

describe('MazoService', () => {
  let service: MazoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MazoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
