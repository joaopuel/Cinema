import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Filme } from 'src/app/types/types';

@Component({
  selector: 'app-cinema-screen',
  templateUrl: './cinema-screen.component.html',
  styleUrls: ['./cinema-screen.component.css']
})
export class CinemaScreenComponent implements OnInit {

  diaSemana: string[] = ["Dom","Seg","Ter","Qua","Qui","Sex","Sáb"];

  diaEscolhido: Date = new Date();

  constructor() { }

  ngOnInit(): void {
  }
  
  getDays = () => {
    var listaDias: Date[] = [];
  
      for(let i=0; i<8; i++){
        let nextDay = new Date();
        nextDay.setDate(nextDay.getDate()+i);
        listaDias.push(nextDay);
      }

    return listaDias;
  }

  today = () => {
    return new Date();
  }

  onClick = (data: Date) => {
    this.diaEscolhido = data;
  }
}
