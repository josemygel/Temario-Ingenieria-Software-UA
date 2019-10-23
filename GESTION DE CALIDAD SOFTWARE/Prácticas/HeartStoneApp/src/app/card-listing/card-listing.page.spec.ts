import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CardListingPage } from './card-listing.page';

describe('CardListingPage', () => {
  let component: CardListingPage;
  let fixture: ComponentFixture<CardListingPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CardListingPage ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CardListingPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
