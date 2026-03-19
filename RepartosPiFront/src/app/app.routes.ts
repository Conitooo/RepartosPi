import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: '',
        loadComponent: () => import('./componentes/login-page/login-page').then(m => m.LoginPageComponent)
    },
    {
        path: 'main',
        loadComponent: () => import('./componentes/main/main').then(m => m.Main)
    }
];
