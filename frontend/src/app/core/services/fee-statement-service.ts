import { HttpClient } from '@angular/common/http';
import { Injectable, Service } from '@angular/core';
import { Observable } from 'rxjs';
import { FeeStatement } from '../../models/feeStatement.model';

@Injectable({ providedIn: 'root'})
export class FeeStatementService {
    constructor(private http: HttpClient) {}

    getFeeStatements(): Observable<FeeStatement[]> {
        return this.http.get<FeeStatement[]>('http://localhost:8080/api/test/feeStatements')
    }
}
