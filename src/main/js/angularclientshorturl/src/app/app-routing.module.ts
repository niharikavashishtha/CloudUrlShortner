import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LongUrlFormComponent } from './shorturl-form/long-url-form.component';
import { ClientEntityFormComponent } from './shorturl-form/client-entity-form.component';

const routes: Routes = [
 { path: 'shortme', component: LongUrlFormComponent },
 { path: 'registerclient', component: ClientEntityFormComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
