import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FavoriteSongCard } from './favorite-song-card';

describe('FavoriteSongCard', () => {
  let component: FavoriteSongCard;
  let fixture: ComponentFixture<FavoriteSongCard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FavoriteSongCard],
    }).compileComponents();

    fixture = TestBed.createComponent(FavoriteSongCard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
