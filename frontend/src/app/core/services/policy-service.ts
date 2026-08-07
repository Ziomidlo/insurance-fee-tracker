import { HttpClient } from '@angular/common/http';
import {  Injectable, Service } from '@angular/core';
import { Observable } from 'rxjs';
import { Policy }  from '../../models/policy.model';

@Injectable({
  providedIn: 'root'
})
export class PolicyService {

  private apiUrl = 'http://localhost:8080/api/test/policies';
    constructor(private http: HttpClient) {}

  getPolicies(): Observable<Policy[]> {
    return this.http.get<Policy[]>(this.apiUrl);
  }
  
  getPolicyById(id: string): Observable<Policy> {
    return this.http.get<Policy>(`${this.apiUrl}/${id}`);
  }
}
