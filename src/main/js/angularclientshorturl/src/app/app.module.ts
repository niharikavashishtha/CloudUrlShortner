import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { LongUrlFormComponent } from './shorturl-form/long-url-form.component';
import { LongUrlService } from './service/long-url.service';
import { ClientEntityFormComponent } from './shorturl-form/client-entity-form.component';
import { ClientEntityService } from './service/client-entity.service';

@NgModule({
  declarations: [
    AppComponent,
    LongUrlFormComponent,
    ClientEntityFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [LongUrlService, ClientEntityService],
  bootstrap: [AppComponent]
})
export class AppModule { }
