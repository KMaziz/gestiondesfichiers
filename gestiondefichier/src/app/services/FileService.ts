import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

const baseUrl = 'http://localhost:8085/api';

@Injectable({
  providedIn: 'root'
})
export class TutorialService {

  constructor(private http: HttpClient) { }

  getAllCategory() {
    return this.http.get(baseUrl+"/category");
  }

  getAllSubCategory() {
    return this.http.get(baseUrl+"/subcategory");
  }

  addCategory(data: any) {
    return this.http.post(baseUrl+"/addacategory", data);
  }


}
