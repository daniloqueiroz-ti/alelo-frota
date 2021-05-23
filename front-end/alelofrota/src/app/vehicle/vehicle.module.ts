import { UpdateVehicleComponent } from './update-vehicle/update-vehicle.component';
import { ListVehicleComponent } from './list-vehicle/list-vehicle.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { VehicleRoutingModule } from './vehicle-routing.module';

import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatCardModule } from '@angular/material/card';
import { MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatRadioModule } from '@angular/material/radio';

@NgModule({
  declarations: [
    ListVehicleComponent,
    UpdateVehicleComponent

  ],
  imports: [
    FormsModule,
    ReactiveFormsModule,
    CommonModule,
    VehicleRoutingModule,
    MatTableModule,
    MatPaginatorModule,
    MatCardModule,    
    MatDialogModule,
    MatIconModule,
    MatInputModule,
    MatFormFieldModule,
    MatButtonModule,
    MatRadioModule
  ],
  exports: [ 
  ]
})
export class VehicleModule { }
