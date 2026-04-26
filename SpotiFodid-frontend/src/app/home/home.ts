import { Component, effect, inject } from '@angular/core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { SongCard } from './song-card/song-card';
import { SongService } from '../service/song';
import { ReadSong } from '../service/model/song.model';
import { Toast } from '../service/toast';

@Component({
  selector: 'app-home',
  imports: [FontAwesomeModule, SongCard],
  templateUrl: './home.html',
  styleUrl: './home.scss',
})
export class Home {
  private songService : SongService = inject(SongService);
  private toastService : Toast = inject(Toast);

  allSongs: Array<ReadSong> | undefined;

  constructor() {
    effect(() => {
      const allSongsResponse = this.songService.getAllSig();
      if (allSongsResponse.status === "OK") {
        this.allSongs = allSongsResponse.value;
      } else if (allSongsResponse.status === "ERROR") {
        this.toastService.show('An error occurred while fetching all songs', 'DANGER');
      }
    });
  }

  ngOnInit() {
    this.songService.getAll();
  }
}
