import { Injectable } from '@angular/core';
import { LogManualModel } from './log-manual/log-manual.model';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AppLogsService {
  
  private baseUrl: string = 'http://localhost:9090/';

  constructor(private http: HttpClient) { }

  public postLog(model: LogManualModel): Observable<any> {
    return this.http.post(this.baseUrl + 'logs/save', model, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
      responseType:'text' as 'json'
    });
  }

  public getLogsByIp(ip: string): Observable<any> {
    return this.http.get(this.baseUrl + 'logs/listByIp/' + ip); 
  }

  public getLogsByIpAndDate(ip: string, startTime: string, endTime: string): Observable<any> {
    return this.http.get(this.baseUrl + 'logs/listByIpAndDate/' + ip + '/' + startTime + '/'+ endTime);
  }

  public getLogsByDate(startTime: string, endTime: string): Observable<any> {
    return this.http.get(this.baseUrl + 'logs/listByDate/' + startTime + '/'+ endTime);
  }

  public getAllLogs(): Observable<any> {
    return this.http.get(this.baseUrl + 'logs/list');
  }

  public countLogsByIpAndDateAndUserAgent(ip: string, startTime: string, endTime: string, userAgent: string): Observable<any> {
    return this.http.get(this.baseUrl + '/logs/countByIpAndDateAndUserAgent/' + ip + '/'  + startTime + '/' + endTime + '/' + userAgent);
  }

  public uploadFile(formData: FormData): Observable<any> {
    console.log(formData);
    return this.http.post(this.baseUrl + 'logs/batch_file?uploadFile=', formData, {
      headers: new HttpHeaders({
        
      })
    });
  }
}
