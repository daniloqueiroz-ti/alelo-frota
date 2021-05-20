import { UpdateVehicleComponent } from './../update-vehicle/update-vehicle.component';
import { VehicleService } from './../service/categoria.service';
import { Vehicle } from '../model/vehicle';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Component({
  selector: 'app-list-vehicle',
  templateUrl: './list-vehicle.component.html',
  styleUrls: ['./list-vehicle.component.scss']
})
export class ListVehicleComponent implements OnInit {

  public lista$: Observable<Vehicle[]>;
  public msgError: string;

  constructor(private vehicleService: VehicleService) { }

  ngOnInit(): void {
    this.msgError = null;
    this.getLista();
  }

  // Chama o serviço para obter todos
  private getLista() {
    this.lista$ = this.vehicleService.getLista()
      .pipe(
        catchError(error => {
          this.msgError = error;
          console.log("Listar com erro : " + error);
          // tslint:disable-next-line: deprecation
          return empty();
        })
      );
  }

    // deletar
    public del(vehicle: Vehicle) {
      this.vehicleService.delete(vehicle.id).subscribe(
        (sucesso) => {
          console.log(sucesso);
          this.getLista();
        },
        error => {
          this.msgError = error;
          console.log("Error no delete : " + error);
        });
    }
  
    public edit(vehicle: Vehicle): void {
      this.dialog.open(UpdateVehicleComponent, {
        width: '50%',
        data: vehicle
      });
      this.dialog.afterAllClosed.subscribe((sucesso) => {
        console.log(sucesso);
        this.getLista();
      },
        error => {
          this.msgError = error;
          console.log("Error no edit : " + error);
        });
    }

}
