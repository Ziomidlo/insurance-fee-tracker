import { TestBed } from '@angular/core/testing';

import { FeeStatementService } from './fee-statement-service';

describe('FeeStatementService', () => {
  let service: FeeStatementService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FeeStatementService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
