import { Component, OnInit } from '@angular/core';
import { PhotographeService } from './service/photographe.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'isophotoFront';

  constructor(private photographeService : PhotographeService){};

  ngOnInit() {
    this.photographeService.publishPseudos();
    
  }
}
