import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FeeStatementsTable } from './fee-statements-table';

describe('FeeStatementsTable', () => {
  let component: FeeStatementsTable;
  let fixture: ComponentFixture<FeeStatementsTable>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FeeStatementsTable],
    }).compileComponents();

    fixture = TestBed.createComponent(FeeStatementsTable);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
