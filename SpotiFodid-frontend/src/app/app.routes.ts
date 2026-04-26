import { Routes } from '@angular/router';
import { AddSongComponent } from './add-song/add-song.component';
import { Home } from './home/home';

export const routes: Routes = [
    {
        path: '',
        component: Home
    },
    {
        path: 'add-song',
        loadComponent: () => import('./add-song/add-song.component').then(m => m.AddSongComponent)
    }
];
