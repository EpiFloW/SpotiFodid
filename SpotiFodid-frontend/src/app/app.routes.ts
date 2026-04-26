import { Routes } from '@angular/router';
import { AddSongComponent } from './add-song/add-song.component';

export const routes: Routes = [
    {
        path: 'add-song',
        loadComponent: () => import('./add-song/add-song.component').then(m => m.AddSongComponent)
    }
];
