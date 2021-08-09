import { Component, OnInit } from '@angular/core';
import { Url } from 'src/app/models/url.model';
import { UrlshortenerService } from 'src/app/services/urlshortener.service';

@Component({
  selector: 'app-retrieve-url',
  templateUrl: './retrieve-url.component.html',
  styleUrls: ['./retrieve-url.component.css']
})
export class RetrieveUrlComponent implements OnInit {

  url: Url = {
    id : '',
    completeUrl: '',
    hashedUrl: ''
  };
  urls?: Url[];

  constructor(private urlshortenerService: UrlshortenerService) { }

  ngOnInit(): void {
  }

  retrieveUrl(): void {
    this.urlshortenerService.findByShortenUrl(this.url.hashedUrl)
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
    this.url.completeUrl = '';
  }

}
