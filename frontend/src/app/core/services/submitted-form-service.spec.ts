import { TestBed } from '@angular/core/testing';

import { SubmittedFormService } from './submitted-form-service';

describe('SubmittedFormService', () => {
  let service: SubmittedFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SubmittedFormService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
