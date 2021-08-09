import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RetrieveUrlComponent } from './components/retrieve-url/retrieve-url.component';
import { ShortenUrlComponent } from './components/shorten-url/shorten-url.component';


const routes: Routes = [
  { path: '', redirectTo: 'urlshortener', pathMatch: 'full' },
  { path: 'shortenUrl', component: ShortenUrlComponent },
  { path: 'retrieveUrl', component: RetrieveUrlComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
