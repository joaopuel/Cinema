import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AuthenticationService } from 'src/app/helpers/auth.service';
import { Cadeira, CadeiraPayload, Sala, User } from 'src/app/types/types';

@Component({
  selector: 'app-sala-screen',
  templateUrl: './sala-screen.component.html',
  styleUrls: ['./sala-screen.component.css']
})
export class SalaScreenComponent implements OnInit {

  user!: User | null;

  sala!: Sala;

  idSala!: string | null;

  cadeirasAlteradas: Cadeira[] = [];

  mudanca: boolean = false;

  novaSala: boolean = false;

  letras = "ABCDEFGHIJKLMNOPQRSTUVYXYZ";

  cadeirasSalaForm: FormGroup = this.formBuilder.group({
    numFileiras: ['', Validators.required],
    tamFileiras: ['', Validators.required]
  });

  cadeiraForm: FormGroup = this.formBuilder.group({
    id: [null],
    codigo: ['', Validators.required],
    tipoCadeira: ['', Validators.required],
    fileira: ['', Validators.required],
    ordemFileira: ['', Validators.required]
  });

  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private authenticationService: AuthenticationService,
    private acttivateRoute: ActivatedRoute
  ) {
    this.authenticationService.user.subscribe(x => this.user = x);
  }

  ngOnInit(): void {
    this.idSala = this.acttivateRoute.snapshot.paramMap.get('id');
    this.http.get<Sala>(`/salas/${this.idSala}`).subscribe((sala) => this.sala = sala);
  }

  getCadeirasByFileira = (fileira: number) => {
    let listaCadeiras: (Cadeira | undefined)[] = [];
    for(let i=1; i <= this.sala.tamFileiras; i++){
      listaCadeiras.push(this.sala.cadeiras.filter((c) => c.fileira === fileira).find((c) => c.ordemFileira === i));
    }
    return listaCadeiras;
  }

  getFileiras = () => {
    return this.range(this.sala.numFileiras, 1);
  }

  range(size: number, startAt: number) {
    let array: number[] = [];
    for(let i=startAt; i<= size; i++){
      array.push(i);
    }
    return array;
  }

  setCadeiras(){
    let listaCadeiras: Cadeira[] = [];
      for(let i=1; i <= this.cadeirasSalaForm.value.numFileiras; i++){
        for(let j=1; j <= this.cadeirasSalaForm.value.tamFileiras; j++){
          let cadeira: Cadeira = {
            id: null,
            codigo: this.letras[i-1] + ((j<10) ? ("0" + j) : j),
            tipoCadeira: "Padrão",
            fileira: i,
            ordemFileira: j
          }
          listaCadeiras.push(cadeira);
        }
      }

    this.sala.numFileiras = this.cadeirasSalaForm.value.numFileiras;
    this.sala.tamFileiras = this.cadeirasSalaForm.value.tamFileiras;
    this.sala.cadeiras = listaCadeiras;
    this.mudanca = true;
    this.novaSala = true;
  }

  onClick(cadeira: Cadeira){
    let index: number = this.sala.cadeiras.indexOf(cadeira);
    this.sala.cadeiras.splice(index, 1);
    let c: Cadeira = cadeira;
    c.tipoCadeira = (cadeira.tipoCadeira == 'Padrão') ? 'VIP' : 'Padrão';
    this.sala.cadeiras.push(c);
    this.mudanca = true;
    this.cadeirasAlteradas.push(c);
  }

  cancelChanges(){
    window.location.reload();
  }

  saveChanges(){
    if(this.novaSala){
      let listaCadeirasPayload: CadeiraPayload[] = [];
      this.sala.cadeiras.forEach((c) => {
        let cadeira: CadeiraPayload = {
          idSala: this.sala.id,
          codigo: c.codigo,
          tipoCadeira: c.tipoCadeira,
          fileira: c.fileira,
          ordemFileira: c.ordemFileira
        }
        listaCadeirasPayload.push(cadeira);
        this.http.post<CadeiraPayload[]>("/cadeiras/listacadeiras", listaCadeirasPayload).subscribe((sucess) => {window.location.reload()}, (error) => {console.log(error)});
      });
    } else{
      this.http.put<Cadeira[]>("/cadeiras/listacadeiras", this.cadeirasAlteradas).subscribe((sucess) => {window.location.reload()}, (error) => {console.log(error)});
    }
  }

  deleteCadeiras(){
    this.http.delete<any>(`/cadeiras/sala/${this.sala.id}`).subscribe((sucess) => {window.location.reload()}, (error) => {console.log(error)});
  }
}
