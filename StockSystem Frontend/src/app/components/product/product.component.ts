import { Component, OnInit } from '@angular/core'
import { FormControl, FormGroup, Validators } from '@angular/forms'
import { NgbModal } from '@ng-bootstrap/ng-bootstrap'
import { Product } from 'src/app/models/product'
import { User } from 'src/app/models/user'
import { ProductService } from 'src/app/services/product.service'
import { UserService } from 'src/app/services/user.service'

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  productList = new Array<Product>()
  userList = new Array<User>()
  productForm: FormGroup
  product = new Product()
  id2: number
  name2: string
  price2: number
  stock2: number
  description2: string
  userId: number

  constructor(private productService: ProductService, private userService: UserService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.name2 = ""
    this.id2 = 0
    this.price2 = 0
    this.stock2 = 0
    this.description2 = ""
    this.userId = 0

    this.productForm = new FormGroup({
      'name': new FormControl(this.product.name, Validators.required),
      'user': new FormControl(this.userId),
      'price': new FormControl(this.product.price),
      'stock': new FormControl(this.product.stock),
      'description': new FormControl(this.product.description)
    })

    this.userService.getAll().subscribe(response => {
      this.userList = response
    }, error => {
      console.log(error)
    })

    this.getAll()
  }

  get name() { return this.productForm.get('name')  }
  get stock() { return this.productForm.get('stock')}
  get price() { return this.productForm.get('price')}
  get description() { return this.productForm.get('description')}
  get user() { return this.productForm.get('user') }

  getAll() {
    this.productService.getAll().subscribe((response) => {
      this.productList = response
      document.getElementsByTagName('input')[0].focus()
    }, (error) => {
      console.log(error)
    })
  }

  viewProduct(product: Product, ver: any) {
    this.id2 = product.id
    this.name2 = product.name
    this.price2 = product.price
    this.stock2 = product.stock
    this.description2= product.description
     
    this.modalService.open(ver).result.then(() => {
      product.name = this.name2
      product.price = this.price2
      product.stock = this.stock2
      product.description = this.description2
      
      this.productService.updateProduct(product).subscribe(() => {
        location.reload()
    
      }, error => {
        console.log(error)
      })
    })
  }

  deleteProduct(id: number) {
    this.productService.deleteProduct(id).subscribe(() => {
      location.reload()
    }, error => {
      console.log(error)
    })
  }

  insertProduct() {
    this.product.name = this.name?.value 
    this.product.stock = this.stock?.value 
    this.product.price = this.price?.value 
    this.product.description = this.description?.value 
    console.log(this.product)
    this.productService.insertProduct(this.product).subscribe(response => { })
    location.reload()
  }
}

    //   this.userService.setProduct(this.user2, response).subscribe(() => {
    //     location.reload()
    //   }, error => {
    //     console.log(error)
    //   })
    // }