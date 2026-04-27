import {Component, effect, inject, OnInit} from '@angular/core';
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";
import {FormsModule} from "@angular/forms";
import {SongContentService} from "../../service/song-content.service";
import {ReadSong, SongContent} from "../../service/model/song.model";
import {Howl} from "howler";
import { SmallSongCard } from '../../shared/small-song-card/small-song-card';
import { FavoriteSongBtn } from '../../shared/favorite-song-btn/favorite-song-btn';
import { YouTubePlayer } from '@angular/youtube-player';

@Component({
  selector: 'app-player',
  imports: [
    FontAwesomeModule,
    FormsModule,
    SmallSongCard,
    FavoriteSongBtn,
    YouTubePlayer
  ],
  templateUrl: './player.html',
  styleUrl: './player.scss',
})
export class Player {
  songContentService = inject(SongContentService);

  public currentSong: SongContent | undefined = undefined;
  currentHowlInstance: Howl | undefined;

  isPlaying: boolean = false;
  currentVolume = 0.5;
  isMuted = false;

  private nextQueue: Array<ReadSong> = [];
  private previousQueue: Array<ReadSong> = [];

  isYouTube = false;
  youtubeVideoId: string | undefined;
  private youtubePlayer: YT.Player | undefined

  ngOnInit() {
    if (!window.document.getElementById('youtube-player-script')) {
      const tag = document.createElement('script');
      tag.id = 'youtube-player-script'; // Ajoute un ID
      tag.src = "https://www.youtube.com/iframe_api";
      document.body.appendChild(tag);
    }
  }
  
  constructor() {
    effect(() => {
      const newQueue = this.songContentService.queueToPlay();
      if (newQueue.length > 0) {
        this.onNewQueue(newQueue);
      }
    }); 

    effect(() => {
      if (this.songContentService.playNewSong().status === "OK") {
        this.onNextSong();
      }
    });
  }


  private onNewQueue(newQueue: Array<ReadSong>): void {
    this.nextQueue = newQueue;
    this.playNextSongInQueue();
  }

private playNextSongInQueue(): void {
  if (this.nextQueue.length > 0) {
    this.isPlaying = false;
    if (this.currentSong) {
      this.previousQueue.unshift(this.currentSong);
    }
    const nextSong = this.nextQueue.shift();
    if (nextSong && nextSong.youtubeVideoId) {
      this.currentSong = nextSong;
      this.onNextSong(); 
    } else if (nextSong) {
      this.songContentService.fetchNextSong(nextSong);
    }
  }
}

private onNextSong(): void {
  const song = this.songContentService.playNewSong().value || this.currentSong;
  if (!song) return;

  this.currentSong = song;
  this.stopAllPlayers();

  if (this.currentSong.youtubeVideoId) {
    this.isYouTube = true;
    this.youtubeVideoId = this.currentSong.youtubeVideoId;
  } else {
    this.isYouTube = false;
    this.playLocalHowl();
  }
}

  private playLocalHowl() {
    this.currentHowlInstance = new Howl({
      src: [`data:${this.currentSong!.fileContentType};base64,${this.currentSong!.file}`],
      volume: this.currentVolume,
      onplay: () => this.onPlay(),
      onpause: () => this.onPause(),
      onend: () => this.onEnd()
    });
    this.currentHowlInstance.play();
  }

  onYouTubeReady(event: YT.PlayerEvent) {
    this.youtubePlayer = event.target;
    this.youtubePlayer.setVolume(this.currentVolume * 100);
  }

  onYouTubeStateChange(event: YT.OnStateChangeEvent) {
    if (event.data === YT.PlayerState.PLAYING) this.isPlaying = true;
    if (event.data === YT.PlayerState.PAUSED) this.isPlaying = false;
    if (event.data === YT.PlayerState.ENDED) this.onEnd();
  }

  private stopAllPlayers() {
    this.currentHowlInstance?.stop();
    this.youtubePlayer?.stopVideo();
  }

  private onPlay(): void {
    if (this.isYouTube) {
      this.youtubePlayer?.playVideo();
    } else {
      this.currentHowlInstance?.play();
    }
    this.isPlaying = true;
  }

  private onPause(): void {
    if (this.isYouTube) {
      this.youtubePlayer?.pauseVideo();
    } else {
      this.currentHowlInstance?.pause();
    }
    this.isPlaying = false;
  }

  private onEnd(): void {
    this.playNextSongInQueue();
    this.isPlaying = false;
  }

  onForward(): void {
    this.playNextSongInQueue();
  }

  onBackward(): void {
    if (this.previousQueue.length > 0) {
      this.isPlaying = false;
      if (this.currentSong) {
        this.nextQueue.unshift(this.currentSong!);
      }
      const previousSong = this.previousQueue.shift();
      this.songContentService.fetchNextSong(previousSong!);
    }
  }

  pause(): void {
    if (this.currentHowlInstance) {
      this.currentHowlInstance.pause();
    }
  }

  play(): void {
    if (this.currentHowlInstance) {
      this.currentHowlInstance.play();
    }
  }

  onMute(): void {
    if (this.currentHowlInstance) {
      this.isMuted = !this.isMuted;
      this.currentHowlInstance.mute(this.isMuted);
      if (this.isMuted) {
        this.currentVolume = 0;
      } else {
        this.currentVolume = 0.5;
        this.currentHowlInstance.volume(this.currentVolume);
      }
    }
  }

  onVolumeChange(newVolume: number): void {
    this.currentVolume = newVolume / 100;
    if (this.currentHowlInstance) {
      this.currentHowlInstance.volume(this.currentVolume);
      if (this.currentVolume === 0) {
        this.isMuted = true;
        this.currentHowlInstance.mute(this.isMuted);
      } else if (this.isMuted) {
        this.isMuted = false;
        this.currentHowlInstance.mute(this.isMuted);
      }
    }
  }
}
