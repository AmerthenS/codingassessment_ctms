import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  template: `
    <div class="container mt-4">
      <h1>Welcome to Cricket Team Management System</h1>
      <p>Manage your cricket players efficiently.</p>
      <a routerLink="/players" class="btn btn-primary">Go to Players</a>
    </div>
  `,
})
export class HomeComponent {}