import { CardService } from '../card/shared/card.service';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';

import { IonicModule } from '@ionic/angular';

import { CardListingPage } from './card-listing.page';


const routes: Routes = [
  {
    path: '',
    component: CardListingPage
  }
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RouterModule.forChild(routes)
  ],
    //Servicio de cartas implementado (Consumo API)
    providers: [
      CardService
    ],
  declarations: [CardListingPage]
})
export class CardListingPageModule {}
