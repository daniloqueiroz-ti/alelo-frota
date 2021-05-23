import { VehiclePage } from './../model/vehicle';
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

  constructor(private httpClient: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  getLista(request: any) {
    const params = request;
    return this.httpClient.get<VehiclePage[]>(this.url, { params });
  }

  getVehicleById(id: number): Observable<Vehicle> {
    return this.httpClient.get<Vehicle>(this.url + '/' + id)
      .pipe(
        retry(2),
        catchError(this.handleError)
      );
  }

  save(vehicle: Vehicle): Observable<Vehicle> {
    return this.httpClient.post<Vehicle>(this.url, vehicle)
      .pipe(
        retry(2),
        catchError(this.handleError)
      );
  }

  update(vehicle: Vehicle): Observable<Vehicle> {
     return this.httpClient.put<Vehicle>(this.url + '/' + vehicle.id, vehicle, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  delete(id: number) {
    console.log(this.url + '/' + id);
    return this.httpClient.delete<Vehicle>(this.url + '/' + id, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  handleError(error: HttpErrorResponse) {
    return throwError(error.error.title);
  }

}
