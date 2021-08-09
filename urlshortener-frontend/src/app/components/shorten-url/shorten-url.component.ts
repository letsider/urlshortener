import { Component, OnInit } from '@angular/core';
import { Url } from 'src/app/models/url.model';
import { UrlshortenerService } from 'src/app/services/urlshortener.service';

@Component({
  selector: 'app-shorten-url',
  templateUrl: './shorten-url.component.html',
  styleUrls: ['./shorten-url.component.css']
})
export class ShortenUrlComponent implements OnInit {

  url: Url = {
    id : '',
    completeUrl: '',
    hashedUrl: ''
  };

  constructor(private urlshortenerService: UrlshortenerService) { }

  ngOnInit(): void {
  }

  shortenUrl(): void {

    this.urlshortenerService.createShortenUrl(this.url.completeUrl)
      .subscribe(
        response => {
          console.log(response);
          this.url = response;
        },
        error => {
          console.log(error);
          this.url = error.error;
        });
  }
  modelChangeFn() {
    this.url.hashedUrl = '';
  }

}
