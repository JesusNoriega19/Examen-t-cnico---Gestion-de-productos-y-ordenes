import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrdenesService {

  private baseUrl = 'http://localhost:8080/ordenes';

  constructor(private http: HttpClient) {}

  crearOrden(body: any): Observable<number> {
    return this.http.post<number>(`${this.baseUrl}`, body);
  }

  consultarOrdenes(body: any): Observable<any[]> {
    return this.http.post<any[]>(`${this.baseUrl}/consultar`, body);
  }

}
