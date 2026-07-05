import { Routes } from '@angular/router';
import { PolicyTableComponent } from './shared/components/policy-table-component/policy-table-component';
import { UploadDashboard } from './shared/components/upload-dashboard/upload-dashboard';

export const routes: Routes = [
    { path: '', component: UploadDashboard},
    { path: 'policies', component: PolicyTableComponent},
    { path: '**', redirectTo: ''}
];
