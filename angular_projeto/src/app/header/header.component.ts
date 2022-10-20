import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { User } from '../types/types';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  @Input() isLogged!: boolean;
  @Output() notLogged = new EventEmitter<boolean>();

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

  logout = () => {
    this.notLogged.emit(false);
  }
}
