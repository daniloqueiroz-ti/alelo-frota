import { UpdateVehicleComponent } from './../update-vehicle/update-vehicle.component';
import { VehicleService } from '../service/vehicle-service';
import { Vehicle } from '../model/vehicle';
import { Component, OnInit } from '@angular/core';
import { EMPTY, Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Component({
  selector: 'app-list-vehicle',
  templateUrl: './list-vehicle.component.html',
  styleUrls: ['./list-vehicle.component.scss']
})
export class ListVehicleComponent implements OnInit {

  public lista$: Vehicle[] = [];
  public msgError: string = "";

  constructor(private vehicleService: VehicleService) { 
  }

  ngOnInit(): void {
    this.getLista();
  }

  // Chama o serviço para obter todos
  private getLista() {
    setTimeout(() => {
    this.vehicleService.getLista()
      .subscribe(
        result => {
          this.lista$ = result;
        }
      )
    }, 400)
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
  
/*    public edit(vehicle: Vehicle): void {
      this.dialog.open(UpdateVehicleComponent, {
        width: '50%',
        data: vehicle
      });
      this.dialog.afterAllClosed.subscribe((sucesso: any) => {
        console.log(sucesso);
        this.getLista();
      },
        error => {
          this.msgError = error;
          console.log("Error no edit : " + error);
        });
    }*/

}
