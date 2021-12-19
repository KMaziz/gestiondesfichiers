import { Component, OnInit } from '@angular/core';
import { ServicesService } from '../services.service'
import {Category} from '../models/Category'
import { SubCategory } from '../models/Subcategory';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
@Component({
  selector: 'app-addfile',
  templateUrl: './addfile.component.html',
  styleUrls: ['./addfile.component.css']
})
export class AddfileComponent implements OnInit {




  list: Category[] = [];
  listSub : SubCategory[] = [];
  selectedLevel?: Category;
  selectedLevelFile!: Category;
  selectedsubFile!: SubCategory;
  fileToUpload: File | null = null;
  fileInfos?: Observable<any>;
  file?: File | null = null
  selectedFiles?: FileList;
  currentFile?: File;
  currentFileUpload: any;
  angForm!: FormGroup;
  subForm!:FormGroup;
  fileForm!:FormGroup;
  C = new Category();
  sub = new SubCategory();

  constructor( private httpClientService: ServicesService,private fb: FormBuilder) {
    this.getAllCategories();
    this.createForm();

 }


 createForm() {
  this.angForm = this.fb.group({
     name: ['', Validators.required ],
  });
  this.subForm = this.fb.group({
    name: ['', Validators.required ],
    select: ['', Validators.required ],

  })
  this.fileForm = this.fb.group(
    {selectcategorie: ['', Validators.required ],
    selectsouscategorie: ['', Validators.required ],
    file: ['', Validators.required ],
  }
  )
}

  ngOnInit(): void {

  }
 add() : void {
   console.log(this.C)
    this.httpClientService.addCategory(this.C)
        .subscribe( data => {
          console.log(data);
          this.getAllCategories();
          this.angForm.get('name')?.reset();
          alert("Catégorie ajoutée avec succès");
        });
  };


    getAllCategories(): void{

    this.httpClientService.getAllCategory().subscribe( data => {
      this.list=data;
      console.log(this.list)

    })
  }

  addsubcategory() : void{
      console.log(this.selectedLevel);
      this.sub.category=this.selectedLevel;

      this.httpClientService.addSubCategory(this.sub).subscribe( data =>  {console.log(data);
      alert("Sous catégorie ajoutée avec succès");
      this.subForm.get('name')?.reset();
      this.subForm.get('select')?.reset();


    }

       )

  }

  selected(): void{
    this.httpClientService.getSubCategoryByCategory(this.selectedLevelFile).subscribe(data =>  {this.listSub=data;console.log(data) })
  }


  selectFile(event: any): void {
    this.selectedFiles = event.target.files;
    this.file =  this.selectedFiles?.item(0) ;
  }
  upload(): void {
    console.log(this.selectedFiles)
    console.log(this.selectedsubFile.id)
    if (this.selectedFiles) {
      const file: File | null = this.selectedFiles.item(0);

      if (file) {
        this.currentFile = file;
        this.currentFileUpload = this.selectedFiles.item(0);
        this.httpClientService.uploadFileToStorage(this.currentFileUpload,this.selectedsubFile.id).subscribe(event => {
        alert("Fichier ajoutée avec succès");
        this.fileForm.get('selectcategorie')?.reset();
        this.fileForm.get('selectsouscategorie')?.reset();
        this.fileForm.get('file')?.reset();
          this.selectedFiles = undefined;
         }
        );
      }

      this.selectedFiles = undefined;
    }
  }

}
