import { ChangeDetectorRef, Component, inject, OnInit } from '@angular/core';
import { PolicyService } from '../../../core/services/policy-service';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { Policy } from '../../../models/policy.model';

@Component({
  selector: 'app-policy-detail-component',
  imports: [RouterLink],
  templateUrl: './policy-detail-component.html',
  styleUrl: './policy-detail-component.css',
})
export class PolicyDetailComponent implements OnInit {

  private policyService = inject(PolicyService);
  private activatedRoute = inject(ActivatedRoute);
  private cdr = inject(ChangeDetectorRef);

  policy : Policy | null = null;
  isLoading = false;
  errorMessage : string | null = null;

  ngOnInit(): void {
    const id = this.activatedRoute.snapshot.paramMap.get('id');
    if(id != null) {
      this.fetchPolicyDetailData(id);
    }
    
  }

  fetchPolicyDetailData(id: string) : void{
    console.log("Fetch data...");
    this.isLoading = true;
    this.errorMessage = null;

    this.policyService.getPolicyById(id).subscribe({
      next: (data: Policy) => {
        console.log("HTTP request succeed. Data received from Java Server", data);
        this.policy = data;
        this.isLoading = false;
        
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.log("Error during receving a data from Java Server");
        this.errorMessage = 'Could not load invoice from Java server.';
        this.isLoading = false;

        this.cdr.detectChanges();
      },
      complete: () => {
        console.log('Stream has been finished.');
      }
    });
  } 
}
