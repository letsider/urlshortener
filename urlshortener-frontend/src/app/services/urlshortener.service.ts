import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Url } from '../models/url.model';

//variable pour pointer sur l'api deploye en local
//const baseUrl = 'http://localhost:8080/url';

//variable pour pointer sur l'api deploye avec docker-compose
const baseUrl = 'http://localhost:25000/url';

@Injectable({
  providedIn: 'root'
})
export class UrlshortenerService {
  constructor(private http: HttpClient) { }

  createShortenUrl(originalUrl: any): Observable<any> {
    return this.http.get<Url>(`${baseUrl}/shortenUrl?originalUrl=${originalUrl}`);
  }

  findByShortenUrl(hashedUrl: any): Observable<any> {
    return this.http.get<Url>(`${baseUrl}/retrieveUrl?hashedUrl=${hashedUrl}`);
  }
}
