import { CardService } from '../card/shared/card.service';
import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Card } from '../card/shared/card.model'


@Component({
  selector: 'app-card-listing',
  templateUrl: './card-listing.page.html',
  styleUrls: ['./card-listing.page.scss'],
})
export class CardListingPage {
  cards: Card[] = [];
  cardDeck:string;
  cardDeckGroup:string;

  constructor(private cardService:CardService, private route:ActivatedRoute) { 
    this.ionViewWillEnter();
    this.getCardByDeck();
  }

  getCardByDeck(){
    this.cardService.getCardByDeck(this.cardDeckGroup, this.cardDeck).subscribe(
      (cartas:Card[]) => 
      {
        this.cards = cartas;
      });
  }

  ionViewWillEnter(){
    this.cardDeckGroup = this.route.snapshot.paramMap.get('cardDeckGroup');
    this.cardDeck = this.route.snapshot.paramMap.get('cardDeck');
  }



}
