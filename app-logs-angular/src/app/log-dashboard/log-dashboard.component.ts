import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { AppLogsService } from '../app-logs.service';

@Component({
  selector: 'app-log-dashboard',
  templateUrl: './log-dashboard.component.html',
  styleUrls: ['./log-dashboard.component.css']
})
export class LogDashboardComponent implements OnInit {

  mainFormGroup: FormGroup;
  
  count: any;

  displayedColumns: string[] = ['ip', 'startTime', 'endTime', 'userAgent'];

  constructor(private _formBuilder: FormBuilder, private service: AppLogsService) { }

  ngOnInit(): void {
    this.mainFormGroup = this._formBuilder.group({
      ip: [''],
      startTime: [''],
      endTime: [''],
      userAgent: ['']
    });
  }

  public calcularNumeroLogs() {
    this.service.countLogsByIpAndDateAndUserAgent(this.mainFormGroup.controls['ip'].value, this.mainFormGroup.controls['startTime'].value,
      this.mainFormGroup.controls['endTime'].value, this.mainFormGroup.controls['userAgent'].value).subscribe(
        (result: any) => {
           this.count = result;
        }, 
        (error: any) => {
          console.log(error);
        }
      );

  }

}
