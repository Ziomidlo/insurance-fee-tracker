import { ChangeDetectorRef, Component, inject, OnInit } from '@angular/core';
import { FeeStatementService } from '../../../../core/services/fee-statement-service';
import { FeeStatement } from '../../../../models/feeStatement.model';

@Component({
  selector: 'app-fee-statements-table',
  standalone:true,
  imports: [],
  templateUrl: './fee-statements-table.html',
  styleUrl: './fee-statements-table.css',
})
export class FeeStatementsTable implements OnInit {
  private feeStatementService = inject(FeeStatementService);
  private cdr = inject(ChangeDetectorRef);

  feeStatements: FeeStatement [] = [];
  isLoading = false;
  errorMessage: string | null = null;

  ngOnInit(): void {
    this.fetchFeeStatements();
  }

  fetchFeeStatements() : void {
    console.log("Start fetching fee Statements from backend...");
    this.isLoading = true;
    this.errorMessage = null;

    this.feeStatementService.getFeeStatements().subscribe({
      next: (data: FeeStatement[]) => {
        console.log("HTTP request succeeded. Data recived from Java server.");
        this.feeStatements = data;
        this.isLoading = false;
        
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Backend connection failed: ', err);
        this.errorMessage = 'Could not load invoices from Java server.';
        this.isLoading = false;

        this.cdr.detectChanges();
      },
      complete: () => {
        console.log('Stream has been finished.');
      }
    });
  }
}
