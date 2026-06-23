import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UploadDashboard } from './upload-dashboard';

describe('UploadDashboard', () => {
  let component: UploadDashboard;
  let fixture: ComponentFixture<UploadDashboard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UploadDashboard],
    }).compileComponents();

    fixture = TestBed.createComponent(UploadDashboard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
