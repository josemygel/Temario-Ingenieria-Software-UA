import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CardDecksPage } from './card-decks.page';

describe('CardDecksPage', () => {
  let component: CardDecksPage;
  let fixture: ComponentFixture<CardDecksPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CardDecksPage ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CardDecksPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
