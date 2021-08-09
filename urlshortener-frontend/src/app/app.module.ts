import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ShortenUrlComponent } from './components/shorten-url/shorten-url.component';
import { RetrieveUrlComponent } from './components/retrieve-url/retrieve-url.component';

@NgModule({
  declarations: [
    AppComponent,
    ShortenUrlComponent,
    RetrieveUrlComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
