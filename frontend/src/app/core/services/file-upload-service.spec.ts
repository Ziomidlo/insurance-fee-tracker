import { TestBed } from '@angular/core/testing';

import { FileUpload } from './file-upload-service';

describe('FileUpload', () => {
  let service: FileUpload;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FileUpload);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
