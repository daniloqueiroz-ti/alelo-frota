import { Vehicle } from '../model/vehicle';
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class VehicleService {

  url = 'http://localhost:8080/vehicle';

  // injetando o HttpClient
  constructor(private httpClient: HttpClient) { }

  // Headers
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  // Obtem todos
  getLista(): Observable<Vehicle[]> {
    return this.httpClient.get<Vehicle[]>(this.url)
      .pipe(
        retry(1),
        catchError(this.handleError));
  }

  // Obtem um pelo id
  getVehicleById(id: number): Observable<Vehicle> {
    return this.httpClient.get<Vehicle>(this.url + '/' + id)
      .pipe(
        retry(2),
        catchError(this.handleError)
      );
  }

  // salvar
  save(vehicle: Vehicle): Observable<Vehicle> {
    return this.httpClient.post<Vehicle>(this.url, vehicle)
      .pipe(
        retry(2),
        catchError(this.handleError)
      );
  }

  // atualizar
  update(vehicle: Vehicle): Observable<Vehicle> {
     return this.httpClient.put<Vehicle>(this.url + '/' + vehicle.id, vehicle, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  // deletar
  delete(id: number) {
    console.log(this.url + '/' + id);
    return this.httpClient.delete<Vehicle>(this.url + '/' + id, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  // Manipulação de erros
  handleError(error: HttpErrorResponse) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Erro ocorreu no lado do client
      errorMessage = error.error.message;
    } else {
      // Erro ocorreu no lado do servidor
      errorMessage = `Código do erro: ${error.status}, ` + `menssagem: ${error.message}`;
    }
    console.log('HandleError errorMessage: ' + errorMessage);
    return throwError(errorMessage);
  }
}
