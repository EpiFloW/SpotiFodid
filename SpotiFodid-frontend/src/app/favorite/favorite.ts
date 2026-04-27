import { Component, effect, inject } from '@angular/core';
import { ReadSong } from '../service/model/song.model';
import { SongContentService } from '../service/song-content.service';
import { SongService } from '../service/song';
import { FavoriteSongBtn } from '../shared/favorite-song-btn/favorite-song-btn';
import { SmallSongCard } from '../shared/small-song-card/small-song-card';

@Component({
  selector: 'app-favorite',
  imports: [
    FavoriteSongBtn,
    SmallSongCard
  ],
  templateUrl: './favorite.html',
  styleUrl: './favorite.scss',
})
export class Favorite {
  favoriteSongs: Array<ReadSong> = [];

  songService = inject(SongService);

  songContentService = inject(SongContentService);

  constructor() {
    effect(() => {
      let addOrRemoveFavoriteSongSig = this.songService.addOrRemoveFavoriteSongSig();
      if(addOrRemoveFavoriteSongSig.status === "OK") {
        this.songService.fetchFavorite();
      }
    });

    effect(() => {
      let favoriteSongState = this.songService.fetchFavoriteSongSig();
      if(favoriteSongState.status === "OK") {
        favoriteSongState.value?.forEach(song => song.favorite = true)
        this.favoriteSongs = favoriteSongState.value!;
      }
    });
  }

  ngOnInit(): void {
    this.songService.fetchFavorite();
  }

  onPlay(firstSong: ReadSong) {
    this.songContentService.createNewQueue(firstSong, this.favoriteSongs);
  }
}
