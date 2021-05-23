import { ListVehicleComponent } from './vehicle/list-vehicle/list-vehicle.component';
import { ReportsComponent } from './reports/reports.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: "",
    component: ListVehicleComponent
  },
  {
    path: "dashboard",
    component: DashboardComponent
  },
  {
    path: "vehicle",
    component: ListVehicleComponent
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
