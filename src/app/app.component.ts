import { Component } from '@angular/core';
import { Category } from 'src/Category';
import { CategoryService } from 'src/CategoryService';
import { ProductService } from 'src/ProductService';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'nimapTask';

  constructor(private categoryService: CategoryService,private productService: ProductService) {}

  categories: Category[] = [];
  newCategory: Category = new Category(0, '', '', 0);
  isEditMode: boolean = false; // Flag to differentiate between add and update
  selectedCategoryId: number | null = null;


  /****  product */
  showProductsForCat:number=0;
  categoryId:number=0;


  /**** pagination */
  categId:number=0;
  page = 0; // Current page
  totalPages = 0; // Total number of pages
  pageSize = 4; // Number of products per page
  loading = false;
  filteredProducts: any[] = []; // List of filtered products



  searchName: string = '';
  minPrice: number | null = null;
  maxPrice: number | null = null;



  ngOnInit(): void {
    this.fetchCategories();
  }

  applyFilters(): void {
    this.categories = this.categories.filter((cate) => {
      const matchesName = cate.name
        .toLowerCase()
        .includes(this.searchName.toLowerCase());
         
        
      return matchesName;
    });
  }


 
 

 
  onCategorySelect(categoryId: number): void {
    this.categId = categoryId;
    this.page = 0;  // Reset to the first page when changing categories
    this.fetchProducts();
  }



  handlePageChange(page: number): void {
    console.log(page,this.totalPages);
    
    if (page >= 0 && page < this.totalPages) {
      this.page = page;  // Update the current page
      this.fetchProducts();  // Refetch products for the selected page
    }
  }
  

  fetchProducts(): void {
    this.loading = true;
    this.productService.getProductsByCategory(this.categoryId, this.page, this.pageSize)
      .subscribe(
        (response) => {
          console.log(response)
          this.filteredProducts = response.content;  // Assuming the response has 'content' field
          this.totalPages = response.totalPages;  // Assuming the response has 'totalPages' field
          this.loading = false;
        },
        (error) => {
          console.error('Error fetching products', error);
          this.loading = false;
        }
      );
  }
  


  fetchCategories(): void {
    this.categoryService.getAllCategories().subscribe((data) => {
      this.categories = data;
    });
  }

  onSubmit(): void {
    if (this.isEditMode && this.selectedCategoryId !== null) {
      console.log(this.newCategory);
      
      this.categoryService
        .updateCategory(this.selectedCategoryId, this.newCategory)
        .subscribe(() => {
          window.alert('Category Updated Successfully');
          this.resetForm();
          this.fetchCategories();
        });
    } else {
      this.categoryService.saveCategory(this.newCategory).subscribe((data) => {
        window.alert('Category Added Successfully');
  this.fetchCategories();
        this.resetForm();
      });
    }
  }

  deleteCategory(id: number): void {
    this.categoryService.deleteCategory(id).subscribe((data) => {
         if(data==1)
           window.alert('Successfully deleted');
         if(data==-1)
          window.alert('Unable to delete');
         if(data==0)
          window.alert(' Category not found');
         if(data==-3)
          window.alert('Category cannot be deleted because it has associated products');
         if(data==-2)
          window.alert('something is wrong');
      this.fetchCategories();
    });
  }

  editCategory(category: Category): void {
    this.isEditMode = true;
    this.selectedCategoryId = category.id;
    this.newCategory = { ...category };
  }

  resetForm(): void {
    this.isEditMode = false;
    this.selectedCategoryId = null;
    this.newCategory = new Category(0, '', '', 0); 
  }





  categoryname:string='';
  addProduct(cateId:number)
  {

    const category = this.categories.find((data) => data.id === cateId); // Adjust 'id' to match your actual key
    if (category) {
      this.categoryname = category.name;
    }  

    this.categoryId=cateId
    this.showProductsForCat=1;
    console.log(cateId," for  add profuct");
    
  }
}
