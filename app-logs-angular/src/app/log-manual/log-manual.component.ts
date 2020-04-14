import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AppLogsService } from '../app-logs.service';
import { LogManualModel } from './log-manual.model';
import { MatSnackBar } from '@angular/material/snack-bar';




@Component({
  selector: 'app-log-manual',
  templateUrl: './log-manual.component.html',
  styleUrls: ['./log-manual.component.css']
})
export class LogManualComponent implements OnInit {

  mainFormGroup: FormGroup;

  constructor(private _formBuilder: FormBuilder, private service: AppLogsService, private _snackBar: MatSnackBar) {

  }

  ngOnInit() {
    this.mainFormGroup = this._formBuilder.group({
      dataHorario: ['', Validators.required],
      ip: ['', Validators.required],
      request: ['', Validators.required],
      status: ['', Validators.required],
      userAgent: ['', Validators.required]
    });  
  }

  public resetForm() {
    this.mainFormGroup.controls['dataHorario'].setValue(''),
    this.mainFormGroup.controls['ip'].setValue(''),
    this.mainFormGroup.controls['request'].setValue(''),
    this.mainFormGroup.controls['status'].setValue(''),
    this.mainFormGroup.controls['userAgent'].setValue('')
  }


  public salvarLog(): void {

    let model: LogManualModel = new LogManualModel(
      this.mainFormGroup.controls['dataHorario'].value,
      this.mainFormGroup.controls['ip'].value,
      this.mainFormGroup.controls['request'].value,
      this.mainFormGroup.controls['status'].value,
      this.mainFormGroup.controls['userAgent'].value
    );
    console.log(model);
    this.service.postLog(model).subscribe(
      (data: any) => {
        console.log(data);
        this.resetForm();
        this._snackBar.open("Log salvo com sucesso!", 'OK', {
          duration: 3000
        });
      },
      (error: any) => {
        console.log(error);
        this.resetForm();
      }
    );
  }
}
