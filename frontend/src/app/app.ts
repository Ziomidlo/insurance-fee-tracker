import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { UploadDashboard } from "./shared/components/upload-dashboard/upload-dashboard";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, UploadDashboard],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('frontend');
}
