import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { AuthComponent } from "./auth/auth.component";
import { ProfileComponent } from "./profile/profile.component";
import { SecureComponent } from "./secure/secure.component";
import { HomeComponent } from "./home/home.component";
import { AmplifyAngularModule, AmplifyService } from "aws-amplify-angular";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
//import UI from "@aws-amplify/ui";

@NgModule({
  declarations: [
    AppComponent,
    AuthComponent,
    ProfileComponent,
    SecureComponent,
    HomeComponent
  ],
  imports: [BrowserModule, AppRoutingModule, AmplifyAngularModule, BrowserAnimationsModule],
  providers: [AmplifyService],
  bootstrap: [AppComponent]
})
export class AppModule {}
