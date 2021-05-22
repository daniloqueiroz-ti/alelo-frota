import { VehicleComponent } from './vehicle.component';
import { UpdateVehicleComponent } from './update-vehicle/update-vehicle.component';
import { ListVehicleComponent } from './list-vehicle/list-vehicle.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { VehicleRoutingModule } from './vehicle-routing.module';

import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';


@NgModule({
  declarations: [
    ListVehicleComponent,
    UpdateVehicleComponent,
    VehicleComponent

  ],
  imports: [
    CommonModule,
    VehicleRoutingModule,
    MatTableModule,
    MatPaginatorModule
  ],
  exports: [
  ]
})
export class VehicleModule { }
