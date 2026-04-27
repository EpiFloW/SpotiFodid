import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FavoriteSongBtn } from './favorite-song-btn';

describe('FavoriteSongBtn', () => {
  let component: FavoriteSongBtn;
  let fixture: ComponentFixture<FavoriteSongBtn>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FavoriteSongBtn],
    }).compileComponents();

    fixture = TestBed.createComponent(FavoriteSongBtn);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
