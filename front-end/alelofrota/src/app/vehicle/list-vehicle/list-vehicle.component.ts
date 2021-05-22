import { TodoDataSource } from './../datasource/vehicle.datasource';
import { VehiclePage } from './../model/vehicle';
import { VehicleService } from '../service/vehicle-service';
import { Vehicle } from '../model/vehicle';
import { Component, ViewChild, OnInit } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { tap } from 'rxjs/operators';


@Component({
  selector: 'app-list-vehicle',
  templateUrl: './list-vehicle.component.html',
  styleUrls: ['./list-vehicle.component.scss']
})
export class ListVehicleComponent implements OnInit {

  displayedColumns = ['Id', 'Plate', 'Model', 'Manufacturer', 'Status'];
  todoDatasource: TodoDataSource;
  @ViewChild(MatPaginator) paginator: MatPaginator;
 
  constructor(private vehicleService: VehicleService) { 
  }

  ngOnInit() {
    this.todoDatasource = new TodoDataSource(this.vehicleService);
    this.todoDatasource.loadTodos();
  }
 
  ngAfterViewInit() {
    this.todoDatasource.counter$
      .pipe(
        tap((count) => {
          this.paginator.length = count;
        })
      )
      .subscribe();
 
    this.paginator.page
      .pipe(
        tap(() => this.loadTodos())
      )
      .subscribe();
  }
 
  loadTodos() {
    this.todoDatasource.loadTodos(this.paginator.pageIndex, this.paginator.pageSize);
  }

  /*// deletar
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
    }*/
  
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
