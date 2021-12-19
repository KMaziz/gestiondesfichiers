import { Component, OnInit } from '@angular/core';
import { ServicesService } from '../services.service';
import {FileModel} from '../models/FileModel'

@Component({
  selector: 'app-file-list',
  templateUrl: './file-list.component.html',
  styleUrls: ['./file-list.component.css']
})
export class FileListComponent implements OnInit {
  listfile: FileModel[] = [];

  constructor(private httpClientService: ServicesService) {    this.getallfiles();
  }

  ngOnInit(): void {
  }



  getallfiles(): void{

    this.httpClientService.getallfiles().subscribe( data => {
      this.listfile=data;
      console.log(this.listfile);


    });
  }
}
