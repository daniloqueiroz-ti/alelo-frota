import { AppComponent } from './app.component';
import { ReportsComponent } from './reports/reports.component';
import { VehicleComponent } from './vehicle/vehicle.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: "",
    component: VehicleComponent
  },
  {
    path: "dashboard",
    component: DashboardComponent
  },
  {
    path: "vehicle",
    component: VehicleComponent
  },
  {
    path: "reports",
    component: ReportsComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
