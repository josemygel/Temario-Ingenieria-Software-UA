import { CardDeck, Card } from './card.model';
import { Injectable } from '@angular/core';

/**
 * Importamos	las	clases	Observable	de	la	librería 'rxjs',	las	HttpClient 
 * y	HttpHeaders de	'@angular/common/http' y	la	clase	CardDeck del	fichero	'./card.model'
 */
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CardService {

  private headers;
  private readonly HS_API_URL = 'https://omgvamp-hearthstone-v1.p.rapidapi.com';
  private readonly API_KEY = 'c4589af145msh4372f44f3c98d03p1a9da4jsn34ba47e03efe';
  
  constructor(private http: HttpClient) {
    this.headers = new HttpHeaders({'X-Mashape-Key': this.API_KEY});
  }

  public getAllCardDecks(): Observable<CardDeck[]>{
    return this.http.get<CardDeck[]>(this.HS_API_URL+'/info', {headers: this.headers});
  }

  public getCardByDeck(cardDeckGroup:string, cardDeck:string): Observable<Card[]>{
    return this.http.get<Card[]>(this.HS_API_URL+'/cards/'+cardDeckGroup+'/'+cardDeck, { headers: this.headers });
  }
}

