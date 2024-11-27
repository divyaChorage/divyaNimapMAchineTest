import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from './Product';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
    url = 'http://localhost:8080/'; 

  constructor(private http: HttpClient) {}

  getAllProducts(cateId:number): Observable<Product[]> {
    return this.http.get<Product[]>(this.url + 'getAllProductsByCategoryId/'+cateId);
  }

  saveProduct(cateId:number,product: Product): Observable<Product> {
    console.log(cateId," ",product);
    
    return this.http.post<Product>(this.url + 'saveProduct'+'/'+cateId, product);
  }

  updateProduct(id: number, product: Product): Observable<Product> {
    return this.http.put<Product>(this.url + 'updateProductById/' + id, product)
  }



  
  deleteProduct(productid: number): Observable<number> {
    return this.http.delete<number>(this.url+'deleteProductById/'+productid);
  }


  getProductsByCategory(categoryId: number, page: number, size: number): Observable<any> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
console.log(params);

    return this.http.get<any>(this.url+'categories/'+categoryId+'/'+"products", {params} );
  }

  
}
