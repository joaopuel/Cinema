import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(private router: Router) {
  }
  title = 'angular_projeto';

  goProducts() {
    this.router.navigate(
      ['/products'],
      { queryParams: { order: 'popular' } }
    );
  }
}
