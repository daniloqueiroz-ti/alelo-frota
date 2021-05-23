import { Vehicle } from './../model/vehicle';
import { VehicleService } from './../service/vehicle-service';
import { Component, OnInit,  Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-update-vehicle',
  templateUrl: './update-vehicle.component.html',
  styleUrls: ['./update-vehicle.component.scss']
})
export class UpdateVehicleComponent implements OnInit {

  public formulario: FormGroup;
  public msgError: string;

  constructor(private vehicleService: VehicleService,
    public dialogRef: MatDialogRef<UpdateVehicleComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Vehicle) { }

  ngOnInit(): void {
    this.msgError = "";
    this.novoFormulario();
    if (this.data != null) {
      this.formulario.get('id')!.setValue(this.data.id);
      this.formulario.get('plate')!.setValue(this.data.plate);
      this.formulario.get('model')!.setValue(this.data.model);
      this.formulario.get('manufacturer')!.setValue(this.data.manufacturer);
      this.formulario.get('color')!.setValue(this.data.color);
      this.formulario.get('status')!.setValue(this.data.status);
    }
  }

  private novoFormulario(): void {
    this.formulario = new FormGroup({
      id: new FormControl(null),
      plate: new FormControl(null, Validators.required),
      model: new FormControl(null, Validators.required),
      manufacturer: new FormControl(null, Validators.required),
      color: new FormControl(null, Validators.required),
      status: new FormControl(true)
    });
  }

  public resetar(): void {
    this.formulario.reset();
    this.msgError = "";
  }

  public save() {
    if (this.formulario.valid) {
      if (this.formulario.get('id')!.value != null) {
        this.vehicleService.update(this.formulario.value).subscribe(
          (sucesso) => {
            console.log(sucesso);
            this.dialogRef.close();
          },
          error => {
            this.msgError = error;
          });
      } else {
        this.vehicleService.save(this.formulario.value).subscribe(
          (sucesso) => {
            console.log(sucesso);
            this.dialogRef.close();
          },
          error => {
            this.msgError = error;
          });
      }
    } else {
      this.msgError = "O formulário não está válido!";
    }
  }

  public verificaValidTouched(campo: any) {
    return !this.formulario.get(campo)!.valid && this.formulario.get(campo)!.touched;
  }

  public aplicaCssErro(campo: any) {
    return {
      'border-red': this.verificaValidTouched(campo)
    };
  }

}
