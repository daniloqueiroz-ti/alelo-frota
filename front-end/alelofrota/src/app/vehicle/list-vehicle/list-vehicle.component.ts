import { Status } from './../model/vehicle';
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

  displayedColumns = ['Id', 'Plate', 'Model', 'Manufacturer', 'Color', 'Status', 'Action'];

  todoDatasource: TodoDataSource;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  public inputSearch: string;
  
  public selectedValue: string = "All";

  statusList: Status[] = [
    { value: 'All' },
    { value: 'Active' },
    { value: 'Inactive' }
  ];

  public msgError: string;

  constructor(public dialog: MatDialog, private vehicleService: VehicleService) {
  }

  ngOnInit() {
    this.msgError = "";
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

  public search() {
    let filter = this.selectedValue;
    if(this.inputSearch != null){
      filter = this.inputSearch;
    }
    this.todoDatasource.loadByFilters(filter);
  }

  public openDialog() {
    this.dialog.open(UpdateVehicleComponent, {
      width: '60%'
    });
    this.dialog.afterAllClosed.subscribe(() => {
      this.todoDatasource.loadTodos();
    });
  }

  public del(vehicle: any) {
    this.vehicleService.delete(vehicle.id).subscribe(
      (sucesso) => {
        this.todoDatasource.loadTodos();
      },
      error => {
        this.msgError = error;
      });
  }

  public edit(vehicle: any): void {
    this.dialog.open(UpdateVehicleComponent, {
      width: '50%',
      data: vehicle
    });
    this.dialog.afterAllClosed.subscribe((sucesso: any) => {
      this.todoDatasource.loadTodos();
    },
      error => {
        this.msgError = error;
      });
  }

}
