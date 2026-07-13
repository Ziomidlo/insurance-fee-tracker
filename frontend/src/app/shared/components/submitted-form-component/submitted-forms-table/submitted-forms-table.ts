import { ChangeDetectorRef, Component, inject, OnInit } from '@angular/core';
import { SubmittedFormService } from '../../../../core/services/submitted-form-service';
import { SubmittedForm } from '../../../../models/subittedForm.model';

@Component({
  selector: 'app-submitted-forms-table',
  standalone: true,
  imports: [],
  templateUrl: './submitted-forms-table.html',
  styleUrl: './submitted-forms-table.css',
})
export class SubmittedFormsTable implements OnInit {
  private submittedFormsService = inject(SubmittedFormService);
  private cdr = inject(ChangeDetectorRef);

  submittedForms: SubmittedForm [] = [];
  isLoading = false;
  errorMessage: string | null = null;

  ngOnInit(): void {
    this.fetchSubmittedForms();
  }

  fetchSubmittedForms() {
    console.log("Start fetching data from backend server...");
    this.isLoading = true;
    this.errorMessage = null;

    this.submittedFormsService.getSubmittedForms().subscribe({
      next: (data : SubmittedForm []) => {
        this.submittedForms = data;
        this.isLoading = false;

        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error("Backend connection failed.", err);
        this.errorMessage = 'Could not load submitted forms from server.';
        this.isLoading = false;
      },
      complete: () => {
        console.log('Stream has been finished.');
      }
    });
  }
}
