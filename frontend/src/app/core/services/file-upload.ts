import { HttpClient } from '@angular/common/http';
import { Injectable, Service } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class FileUpload {
    constructor(private http: HttpClient) {}
    
    uploadPdf(file: File): Observable<string> {
        const formData = new FormData();

        formData.append('file', file, file.name);

        return this.http.post('http://localhost:8080/api/test/upload', formData, {responseType: 'text'});
    }
}
