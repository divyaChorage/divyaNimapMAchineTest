import { Component } from '@angular/core';
import { Product } from 'src/Product';
import { ProductService } from 'src/ProductService';
import { AppComponent } from '../app.component';


@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent {

  constructor(private productService: ProductService,public app:AppComponent) {}

categpryName=this.app.categoryname;
  products: Product[] = [];
  newProduct: Product= new Product(0, '', '', 0);
  isEditMode: boolean = false; // Flag to differentiate between add and update
  selectedCategoryId: number | null = null;

  ngOnInit(): void {
    this.fetchCategories();
  }

  
  fetchCategories(): void {
    this.productService.getAllProducts(this.app.categoryId).subscribe((data: Product[]) => {
      this.products = data;
    });
  }

  onSubmit(): void {
    console.log(this.app.categoryId+" "+this.newProduct);

    if (this.isEditMode && this.selectedCategoryId !== null) {
      
      this.productService
        .updateProduct(this.selectedCategoryId, this.newProduct)
        .subscribe(() => {
          window.alert('Category Updated Successfully');
          this.resetForm();
          this.fetchCategories();
        });
    } else {
      this.productService.saveProduct(this.app.categoryId,this.newProduct).subscribe(() => {
        window.alert('product Added Successfully');
        this.resetForm();
        this.fetchCategories();
      });
    }
  }


  editCategory(product: Product): void {
    this.isEditMode = true;
    this.selectedCategoryId = product.id;
    this.newProduct = { ...product };
  }

  resetForm(): void {
    this.isEditMode = false;
    this.selectedCategoryId = null;
    this.newProduct = new Product(0, '', '', 0); 
  }


  deleteCategory(id: number): void {
    console.log(id);
    
    this.productService.deleteProduct(id).subscribe(() => {
      window.alert('Category Deleted Successfully');
      this.fetchCategories();
    });
  }

}
