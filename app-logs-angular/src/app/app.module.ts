import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LogManualComponent } from './log-manual/log-manual.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatFormFieldModule }   from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatTableModule } from '@angular/material/table';

import { HttpClientModule }    from '@angular/common/http';
import { FiltroLogComponent } from './filtro-log/filtro-log.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { OverlayModule } from '@angular/cdk/overlay';
import { LogArquivoComponent } from './log-arquivo/log-arquivo.component';
import { LogDashboardComponent } from './log-dashboard/log-dashboard.component';


@NgModule({
  declarations: [
    AppComponent,
    LogManualComponent,
    FiltroLogComponent,
    LogArquivoComponent,
    LogDashboardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    OverlayModule,
    MatTableModule
  ],
  providers: [
    MatSnackBar
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
