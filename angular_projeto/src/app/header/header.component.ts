import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { AuthenticationService } from '../helpers/auth.service';
import { User } from '../types/types';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  user: User | null = null;

  menuButton: Boolean = true;

  constructor(private authenticationService: AuthenticationService) {
    this.authenticationService.user.subscribe(x => this.user = x);
  }

  ngOnInit(): void {
  }

  changeMenu = () => {
    if(this.menuButton){
      this.menuButton = false;
    } else {
      this.menuButton = true;
    }
  }

  logout() {
    this.authenticationService.logout();
}
}
