import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LogManualComponent } from './log-manual/log-manual.component';
import { FiltroLogComponent } from './filtro-log/filtro-log.component';
import { LogArquivoComponent } from './log-arquivo/log-arquivo.component';
import { LogDashboardComponent } from './log-dashboard/log-dashboard.component';


const routes: Routes = [
  { path: 'log-manual', component: LogManualComponent },
  { path: 'filtro-log', component: FiltroLogComponent },
  { path: 'log-arquivo', component: LogArquivoComponent },
  { path: 'log-dashboard', component: LogDashboardComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
