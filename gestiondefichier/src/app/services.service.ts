import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Category } from './models/Category';
import { SubCategory } from './models/Subcategory';
import { FileModel } from './models/FileModel';

const baseUrl = 'http://localhost:8090/api';

@Injectable({
  providedIn: 'root'
})
export class ServicesService {
  constructor(private http: HttpClient) { }

  getAllCategory() {
    return this.http.get<Category[]>(baseUrl+"/category");
  }

  getAllSubCategory() {
    return this.http.get(baseUrl+"/subcategory");
  }

  addCategory(data: any) {
    return this.http.post<any>(baseUrl+"/addacategory", data);
  }
  addSubCategory(data: any) {
    return this.http.post<SubCategory>(baseUrl+"/addsubcategory", data);
  }


  getSubCategoryByCategory(data : Category){
    return this.http.post<any>(baseUrl+"/subcategorybycategoy", data);
  }

  uploadFileToStorage(file: File,s :String){
    const data: FormData = new FormData();
    data.append('file', file);



    return this.http.post<any>(baseUrl+"/uploadFile/"+s, data);

  }

  getallfiles(){
    return this.http.get<FileModel[]>(baseUrl+"/files");

  }
}
