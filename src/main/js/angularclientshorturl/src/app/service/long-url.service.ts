import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { LongUrl } from '../model/long-url';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LongUrlService {
  private applicationUrl: string;

    constructor(private http: HttpClient) {
      this.applicationUrl = 'http://localhost:8080/cloudurl/';
    }

    public save(longUrl: LongUrl): any {
      console.log('calling cloudurl shortme service');
      return this.http.post<LongUrl>(this.applicationUrl + "short-me", longUrl);
    }
}
