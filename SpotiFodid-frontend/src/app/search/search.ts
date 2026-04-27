import {Component, inject} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";
import {SmallSongCard} from "../shared/small-song-card/small-song-card";
import {SongService} from "../service/song";
import {SongContentService} from "../service/song-content.service";
import {Toast} from "../service/toast";
import {ReadSong} from "../service/model/song.model";
import {debounce, filter, interval, of, switchMap, tap} from "rxjs";
import {HttpErrorResponse} from "@angular/common/http";
import {State} from "../service/model/state.model";
import { FavoriteSongBtn } from '../shared/favorite-song-btn/favorite-song-btn';

import { forkJoin } from 'rxjs';
import { YouTubeVideo } from '../service/model/youtubeVideo.model';


@Component({
  selector: 'app-search',
  imports: [
        FormsModule,
        FontAwesomeModule,
        SmallSongCard,
        FavoriteSongBtn
  ],
  templateUrl: './search.html',
  styleUrl: './search.scss',
})
export class Search {
    youtubeResults: Array<YouTubeVideo> = [];
    searchTerm = '';

    private songService = inject(SongService);
    private songContentService = inject(SongContentService);
    private toastService = inject(Toast);

    songsResult: Array<ReadSong> = [];

    isSearching = false;

    onSearch(newSearchTerm: string) {
        this.searchTerm = newSearchTerm;
        if (newSearchTerm.length === 0) {
            this.songsResult = [];
            this.youtubeResults = [];
            return;
    }
    of(newSearchTerm).pipe(
        debounce(() => interval(300)),
        tap(() => this.isSearching = true),
        switchMap(term => forkJoin({
            local: this.songService.search(term),
            youtube: this.songService.searchYouTube(term) 
        }))
    ).subscribe({
        next: (res) => {
            this.isSearching = false;
            if (res.local.status === "OK") this.songsResult = res.local.value!;
            this.youtubeResults = res.youtube;
        },
        error: () => {
            this.isSearching = false;
            this.toastService.show('Error during search', "DANGER");
        }
    });
}

onPlayYouTube(video: YouTubeVideo) {
    const youtubeSong: ReadSong = {
        publicId: video.videoId,
        title: { value: video.title },
        author: { value: "YouTube Music" },
        favorite: false,
        displayPlay: true,
        youtubeVideoId: video.videoId,
        cover: video.thumbnailUrl, 
        coverContentType: 'image/jpeg',
        file: null,
        fileContentType: null
    };
    this.songContentService.createNewQueue(youtubeSong, [youtubeSong]);
}

    private resetResultIfEmptyTerm(newSearchTerm: string) {
        if (newSearchTerm.length === 0) {
            this.songsResult = [];
        }
    }

    onPlay(firstSong: ReadSong) {
        this.songContentService.createNewQueue(firstSong, this.songsResult);
    }

    private onNext(searchState: State<Array<ReadSong>, HttpErrorResponse>) {
        this.isSearching = false;
        if(searchState.status === "OK") {
            this.songsResult = searchState.value!;
        } else if (searchState.status === "ERROR") {
            this.toastService.show('An error occured while searching', "DANGER");
        }
    }
}
