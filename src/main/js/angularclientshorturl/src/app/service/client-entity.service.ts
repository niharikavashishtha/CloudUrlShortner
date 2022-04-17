import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ClientEntity } from '../model/client-entity';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClientEntityService {
  private applicationUrl: string;

  constructor(private http: HttpClient) {
    this.applicationUrl = 'http://localhost:8080/cloudurl/';
  }

  public save(clientEntity: ClientEntity) {
    console.log('adding clientEntity');
    return this.http.post<ClientEntity>(this.applicationUrl + "registerclient", clientEntity);
  }
}
