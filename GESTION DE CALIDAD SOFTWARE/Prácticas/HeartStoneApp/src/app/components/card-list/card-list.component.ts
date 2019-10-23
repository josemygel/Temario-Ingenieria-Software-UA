import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-card-list',
  templateUrl: './card-list.component.html',
  styleUrls: ['./card-list.component.scss'],
})
export class CardListComponent {
//En	el	componente	definimos	dos	input	properties denominados	items es	un	
//array de	tipo	any y	listName de	tipo	string
  @Input('items') items:any[] = [];
  @Input('listName') listName:string;
  @Input() navigateTo:()=>{};
}
