import { Component } from '@angular/core';
import { CardDeck } from '../card/shared/card.model';
import { CardService } from '../card/shared/card.service';
import { Router } from '@angular/router'


@Component({
  selector: 'app-card-decks',
  templateUrl: './card-decks.page.html',
  styleUrls: ['./card-decks.page.scss'],
})
export class CardDecksPage { // implements OnInit {
  ALLOWED_DECKS = ['classes','factions','qualities','types','races'];
  cardDecks:CardDeck[] = [];
  

  constructor(private cardService:CardService, private router: Router) {
    this.getCardDecks();
  }


  extractAllowedDecks (cardDecks: CardDeck[]){
    this.ALLOWED_DECKS.forEach ((deckName:string) => {
      this.cardDecks.push({name:deckName, types:cardDecks[deckName]});
      //debugger;
    })
  }
  
  generateURL(cardDeckGroup:string,cardDeck:string){
    return "/tabs/cards/card-listing/" + cardDeckGroup +	"/" + cardDeck;
  }

  private getCardDecks() {
    this.cardService.getAllCardDecks().subscribe(
      (cardDeckService:CardDeck[])=>{
        this.extractAllowedDecks(cardDeckService);
      }
    )
  }

  public alerta = function(){
    alert("The cardDecks = " + JSON.stringify(this.cardDecks));
  };

//  ngOnInit() {}

}
