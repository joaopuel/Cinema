import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sessoes-screen',
  templateUrl: './sessoes-screen.component.html',
  styleUrls: ['./sessoes-screen.component.css']
})
export class SessoesScreenComponent implements OnInit {

  diaSemana: string[] = ["Dom","Seg","Ter","Qua","Qui","Sex","Sáb"];

  dataHoje: string = new Date().getDate() + '/' + new Date().getMonth();

  constructor() { }

  ngOnInit(): void {
  }
  
  getDays = (primeiro: boolean) => {
    var listaDias: Date[] = [];

    if(primeiro){
      for(let i=1; i<4; i++){
        let nextDay = new Date();
        nextDay.setDate(nextDay.getDate()+i);
        listaDias.push(nextDay);
      }
    } else {
      for(let i=4; i<9; i++){
        let nextDay = new Date();
        nextDay.setDate(nextDay.getDate()+i);
        listaDias.push(nextDay);
      }
    }

    return listaDias;
  }
}
