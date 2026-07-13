import { HttpClient } from '@angular/common/http';
import { Injectable, Service } from '@angular/core';
import { Observable } from 'rxjs';
import { SubmittedForm } from '../../models/subittedForm.model';

@Injectable({ providedIn: 'root'})
export class SubmittedFormService {
    constructor(private http: HttpClient) {}

    getSubmittedForms(): Observable<SubmittedForm[]> {
        return this.http.get<SubmittedForm[]>('http://localhost:8080/api/test/forms')
    }
}
