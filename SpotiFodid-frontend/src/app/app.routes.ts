import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: '',
        loadComponent: () => import('./home/home').then(m => m.Home)
    },
    {
        path: 'add-song',
        loadComponent: () => import('./add-song/add-song.component').then(m => m.AddSongComponent)
    },
    {
        path: 'search',
        loadComponent: () => import('./search/search').then(m => m.Search)
    },
    {
        path: 'favorites',
        loadComponent: () => import('./favorite/favorite').then(m => m.Favorite)
    }
];
