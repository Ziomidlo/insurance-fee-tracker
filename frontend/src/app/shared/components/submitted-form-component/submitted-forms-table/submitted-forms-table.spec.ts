import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubmittedFormsTable } from './submitted-forms-table';

describe('SubmittedFormsTable', () => {
  let component: SubmittedFormsTable;
  let fixture: ComponentFixture<SubmittedFormsTable>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SubmittedFormsTable],
    }).compileComponents();

    fixture = TestBed.createComponent(SubmittedFormsTable);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
