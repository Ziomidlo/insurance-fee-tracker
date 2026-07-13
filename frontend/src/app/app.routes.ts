import { Routes } from '@angular/router';
import { PolicyTableComponent } from './shared/components/policy-table-component/policy-table-component';
import { UploadDashboard } from './shared/components/upload-dashboard/upload-dashboard';
import { FeeStatementsTable } from './shared/components/fee-statement-component/fee-statements-table/fee-statements-table';

export const routes: Routes = [
    { path: '', component: UploadDashboard},
    { path: 'policies', component: PolicyTableComponent},
    { path: 'fees', component: FeeStatementsTable},
    { path: '**', redirectTo: ''}
];
