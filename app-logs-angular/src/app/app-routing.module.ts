import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LogManualComponent } from './log-manual/log-manual.component';
import { FiltroLogComponent } from './filtro-log/filtro-log.component';
import { LogArquivoComponent } from './log-arquivo/log-arquivo.component';


const routes: Routes = [
  { path: 'log-manual', component: LogManualComponent },
  { path: 'filtro-log', component: FiltroLogComponent },
  { path: 'log-arquivo', component: LogArquivoComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
