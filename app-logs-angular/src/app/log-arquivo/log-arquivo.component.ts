import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { AppLogsService } from '../app-logs.service';

const newLocal = 'selectedFile';
@Component({
  selector: 'app-log-arquivo',
  templateUrl: './log-arquivo.component.html',
  styleUrls: ['./log-arquivo.component.css']
})
export class LogArquivoComponent implements OnInit {

  @ViewChild('selectedFile') selectedFileElement: ElementRef;

  constructor(private service: AppLogsService) { }

  ngOnInit(): void {
  }

  public fileChange(event: Event){
    let file: File = this.selectedFileElement.nativeElement.files[0];
    console.log(file);
    let formData:FormData = new FormData();
    formData.append('uploadFile', file, file.name);
    this.service.uploadFile(formData).subscribe(
      (result: string) => {
        console.log(result);
      },
      (error: any) => {
        console.log(error);
      }  
    );
  }

}
