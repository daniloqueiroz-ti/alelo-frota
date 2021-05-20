import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { VehicleComponent } from './vehicle/vehicle.component';
import { ReportsComponent } from './reports/reports.component';
import { ListVehicleComponent } from './vehicle/list-vehicle/list-vehicle.component';
import { UpdateVehicleComponent } from './vehicle/update-vehicle/update-vehicle.component';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    VehicleComponent,
    ReportsComponent,
    ListVehicleComponent,
    UpdateVehicleComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
