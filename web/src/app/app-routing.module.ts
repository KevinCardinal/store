import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';


const routes: Routes = [
    {
        path: 'calculator',
        loadChildren: './features/dofus/dofus.module#DofusModule'
    },
    {
        path: '',
        redirectTo: '/dofus/xp',
        pathMatch: 'full'
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {}
