

import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Category } from './Category';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
   url = 'http://localhost:8080/'; 

  constructor(private http: HttpClient) { }



  saveCategory(cate: Category): Observable<String> {    
    return this.http.post<String>(this.url + 'save', cate);
  }

  deleteCategory(id: number): Observable<number> {
    return this.http.delete<number>(this.url+'deleteCatById/'+id);
  }

  updateCategory(id: number, cate: Category): Observable<Category> {
    console.log(cate+"indide the servuce ");
    
    return this.http.put<Category>(this.url + 'updateCateById/' + id, cate);
  }
  


  getAllCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(this.url + 'getAllCategories');
  }


 


 
}
