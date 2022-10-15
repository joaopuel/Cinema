import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  menuButton: Boolean = true;

  constructor() { }

  ngOnInit(): void {
  }

  changeMenu = () => {
    if(this.menuButton){
      this.menuButton = false;
    } else {
      this.menuButton = true;
    }
  }
}
