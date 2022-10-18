import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-carta',
  templateUrl: './carta.component.html',
  styleUrls: ['./carta.component.css'],
})
export class CartaComponent implements OnInit {
  @Input() numero: string = '';
  @Input() palo: string = '';
  @Input() valor: number = 0;
  @Input() orden: number = 0;
  @Input() index: number = 0;
  @Input() visible: boolean = false;

  hover: boolean = false;

  constructor() {}

  ngOnInit(): void {}

  offset() {
    return this.index * -34;
  }

  setHover(flag: boolean) {
    this.hover = flag;
  }

  mostrarPalo() {
    switch (this.palo) {
      case 'hearts':
        return '♥';
      case 'clubs':
        return '♣';
      case 'spades':
        return '♠';
      case 'diamonds':
        return '♦';
    }
    return '';
  }
}
