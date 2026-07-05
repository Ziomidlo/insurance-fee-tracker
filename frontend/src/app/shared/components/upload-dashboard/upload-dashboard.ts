import { Component } from '@angular/core';
import { FileUpload } from '../../../core/services/file-upload-service';

@Component({
  selector: 'app-upload-dashboard',
  imports: [],
  templateUrl: './upload-dashboard.html',
  styleUrl: './upload-dashboard.css',
})
export class UploadDashboard {
  selectedFile: File | null = null;
  isUploading: boolean = false;
  uploadMessage: string = '';

  constructor(private uploadService: FileUpload) {}

  onFileSelected(event: any) {
    const file: File = event.target.files[0];
    if(file) {
      this.selectedFile = file;
    }
  }

  onUpload() {
    if(!this.selectedFile) return;
    else {
      this.isUploading = true;
      this.uploadMessage = 'Uploading to backend...';

      this.uploadService.uploadPdf(this.selectedFile).subscribe({
          next: (response: string) => {
            this.uploadMessage = `Success: ${response}`;
            this.isUploading = false;
          },
          error: (err) => {
            console.error(err);
            this.uploadMessage = 'Upload failed. Check the console.';
            this.isUploading = false;
          }

      });
    }
  }

}
