import { UpdateVehicleComponent } from './../update-vehicle/update-vehicle.component';
import { TodoDataSource } from './../datasource/vehicle.datasource';
import { VehicleService } from '../service/vehicle-service';
import { Component, ViewChild, OnInit } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { tap } from 'rxjs/operators';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-list-vehicle',
  templateUrl: './list-vehicle.component.html',
  styleUrls: ['./list-vehicle.component.scss']
})
export class ListVehicleComponent implements OnInit {

  displayedColumns = ['Id', 'Plate', 'Model', 'Manufacturer', 'Color', 'Status'];

  todoDatasource: TodoDataSource;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  public msgError: string;
 
  constructor(public dialog: MatDialog, private vehicleService: VehicleService) { 
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

  public openDialog() {
    this.dialog.open(UpdateVehicleComponent, {
      width: '60%'
    });
    this.dialog.afterAllClosed.subscribe(() => {
      this.todoDatasource.loadTodos();
    });
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
