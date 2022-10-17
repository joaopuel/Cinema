import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-filmes-screen',
  templateUrl: './filmes-screen.component.html',
  styleUrls: ['./filmes-screen.component.css']
})
export class FilmesScreenComponent implements OnInit {

  emCartaz: boolean = true;

  constructor() { }

  ngOnInit(): void {
  }

  changeEmCartaz = () => {
    if(this.emCartaz){
      this.emCartaz = false;
    } else {
      this.emCartaz = true;
    }
  }

}
