import { Component, inject, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {FaIconLibrary, FontAwesomeModule} from "@fortawesome/angular-fontawesome"
import {fontAwesomeIcons} from "./shared/font-awesome-icons";
import {Navigation} from "./layout/navigation/navigation";
import { Library } from './layout/library/library';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, FontAwesomeModule, Navigation, Library],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected readonly title = signal('SpotiFodid-frontend');

  private faIconLibrary = inject(FaIconLibrary);

  ngOnInit(): void {
    this.initFontAwesome();
  }

  private initFontAwesome() {
    this.faIconLibrary.addIcons(...fontAwesomeIcons);
  }
}
