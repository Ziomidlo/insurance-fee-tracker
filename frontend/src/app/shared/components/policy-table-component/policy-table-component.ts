import { HttpClient } from '@angular/common/http';
import { ChangeDetectorRef, Component, inject, OnInit } from '@angular/core';
import { PolicyService } from '../../../core/services/policy-service';
import { Policy } from '../../../models/policy.model';

@Component({
  selector: 'app-policy-table-component',
  standalone: true,
  imports: [],
  templateUrl: './policy-table-component.html',
  styleUrl: './policy-table-component.css',
})
export class PolicyTableComponent implements OnInit {

  private policyService = inject(PolicyService);
  private cdr = inject(ChangeDetectorRef);

  policies: Policy[] = [];
  isLoading = false;
  errorMessage: string | null = null;

  ngOnInit(): void {
    this.fetchBackendData();
  }

  fetchBackendData() : void {
    console.log('Starting fetch backend data...');
    this.isLoading = true;
    this.errorMessage = null;

    this.policyService.getPolicies().subscribe({
      next: (data: Policy[]) => {
        console.log("HTTP Request succeded. Data received from Java: ", data);
        this.policies = data;
        this.isLoading = false;
        console.log("Data assigned to HTML table."); 
        
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Backend connection failed: ', err);
        this.errorMessage = 'Colund not load invoices from Java server.';
        this.isLoading = false;

        this.cdr.detectChanges();
      }
    });
  }
}
