import { Component, OnInit } from '@angular/core';
import { AppLogsService } from '../app-logs.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-filtro-log',
  templateUrl: './filtro-log.component.html',
  styleUrls: ['./filtro-log.component.css']
})
export class FiltroLogComponent implements OnInit {

  displayedColumns: string[] = ['id', 'date', 'ip', 'request', 'status', 'user_agent'];

  dataArray: any[];

  mainFormGroup: FormGroup;

  constructor(private _formBuilder: FormBuilder, private service: AppLogsService) { }

  ngOnInit(): void {
    this.mainFormGroup = this._formBuilder.group({
      ip: [''],
      startTime: [''],
      endTime: ['']
    });
  }

  public pesquisarLogs() {
    if(this.mainFormGroup.controls['ip'].value != '' && this.mainFormGroup.controls['startTime'].value != '' && this.mainFormGroup.controls['endTime'].value!= '') {
      this.service.getLogsByIpAndDate(this.mainFormGroup.controls['ip'].value, 
        this.mainFormGroup.controls['startTime'].value, this.mainFormGroup.controls['endTime'].value).subscribe(
          (result: any[]) => {
            this.dataArray = result;
      });
    } else if(this.mainFormGroup.controls['ip'].value == '' && this.mainFormGroup.controls['startTime'].value != '' && this.mainFormGroup.controls['endTime'].value!= '') {
      this.service.getLogsByDate(this.mainFormGroup.controls['startTime'].value, this.mainFormGroup.controls['endTime'].value).subscribe(
          (result: any[]) => {
           this.dataArray = result;
      });
    } else if(this.mainFormGroup.controls['ip'].value != '' && this.mainFormGroup.controls['startTime'].value == '' && this.mainFormGroup.controls['endTime'].value == '') {
      this.service.getLogsByIp(this.mainFormGroup.controls['ip'].value).subscribe(
        (result: any[]) => {
         this.dataArray = result;
      });
     } else if(this.mainFormGroup.controls['ip'].value == '' && this.mainFormGroup.controls['startTime'].value == '' && this.mainFormGroup.controls['endTime'].value == '')
      this.service.getAllLogs().subscribe(
          (result: any[]) => {
            this.dataArray = result;
    });
  }
  
}
