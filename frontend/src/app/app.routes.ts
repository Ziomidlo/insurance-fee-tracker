import { Routes } from '@angular/router';
import { PolicyTableComponent } from './shared/components/policy-table-component/policy-table-component';
import { UploadDashboard } from './shared/components/upload-dashboard/upload-dashboard';
import { FeeStatementsTable } from './shared/components/fee-statement-component/fee-statements-table/fee-statements-table';
import { SubmittedFormsTable } from './shared/components/submitted-form-component/submitted-forms-table/submitted-forms-table';
import { PolicyDetailComponent } from './shared/components/policy-detail-component/policy-detail-component';

export const routes: Routes = [
    { path: '', component: UploadDashboard},
    { path: 'policies', component: PolicyTableComponent},
    { path: 'policy/:id', component: PolicyDetailComponent},
    { path: 'fees', component: FeeStatementsTable},
    { path: 'forms', component: SubmittedFormsTable},
    { path: '**', redirectTo: ''}
];
