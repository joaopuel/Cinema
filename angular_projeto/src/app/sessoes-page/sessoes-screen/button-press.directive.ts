import { Directive, ElementRef, OnInit, Renderer2 } from '@angular/core';

@Directive({
  selector: '[buttonPress]'
})
export class ButtonDirective implements OnInit {

  constructor(private elRef: ElementRef, private renderer: Renderer2) {

  }

  ngOnInit() {
  }

@HostListener('mousedown') onmousedown(eventData: Event) {
   if (this.elRef.nativeElement.style.backgroundColor === 'blue') {
     this.renderer.setStyle(this.elRef.nativeElement, 'background-color', 'rgba(200,0,0,0.7)');
   } else {
     this.renderer.setStyle(this.elRef.nativeElement, 'background-color', 'blue');

   }

}
