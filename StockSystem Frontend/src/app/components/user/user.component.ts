import { Component, OnInit } from '@angular/core'
import { FormControl, FormGroup, Validators } from '@angular/forms'
import { NgbModal } from '@ng-bootstrap/ng-bootstrap'
import { Product } from 'src/app/models/product'
import { User } from 'src/app/models/user'
import { ProductService } from 'src/app/services/product.service'
import { UserService } from 'src/app/services/user.service'

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  userList =  new Array<User>()
  productList = new Array<Product>()
  userForm: FormGroup
  user2 = new User()
  productList2 = new Array<Product>()
  product: Product
  id2: number
  name2: string
  nickname2: string
  password2: string
  


  constructor(private productService: ProductService, private userService: UserService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.name2 = ""
    this.id2 = 0
    this.nickname2 = ""
    this.password2 = ""
    

    this.userForm = new FormGroup({
      'id': new FormControl(this.id2),
      'name': new FormControl(this.name2, Validators.required),
      'nickname': new FormControl(this.nickname2,Validators.required),
      'password': new FormControl(this.password2,Validators.required),
      'productList': new FormControl()


    })

    this.userService.getAll().subscribe(response => {
      this.userList = response
    }, error => {
      console.log(error)
    })

    this.getAll()
    this.getAllP()
  }

  get name() { return this.userForm.get('name')  }
  get nickname() { return this.userForm.get('nickname')}
  get password() { return this.userForm.get('password')}
  get id() { return this.userForm.get('id')}


  getAll() {
    this.userService.getAll().subscribe((response) => {
      this.userList = response
      document.getElementsByTagName('input')[0].focus()
    }, (error) => {
      console.log(error)
    })
  }

  getAllP(){
    this.productService.getAll().subscribe((response) => {
      this.productList2 = response
      document.getElementsByTagName('input')[0].focus()
    }, (error)=>{
      console.log(error)
    })
  }

  viewUser(user: User, ver: any) {
    this.id2 = user.id
    this.name2 = user.name
    this.nickname2 = user.nickName
    this.password2 = user.password
     
    this.modalService.open(ver).result.then(() => {
      user.name = this.name2
      user.nickName = this.nickname2
      user.password = this.password2
   
      
      this.userService.updateUser(user).subscribe(() => {
       
    
      }, error => {
        console.log(error)
      })
    })
  }

  deleteUser(id: number) {
    this.userService.deleteUser(id).subscribe(() => {
      location.reload()
    }, error => {
      console.log(error)
    })
  }

  insertUser() {
    this.user2.name = this.name?.value;
    this.user2.password = this.password?.value;
    this.user2.nickName = this.nickname?.value;
  
    // Añadir este log para verificar los datos antes de la inserción
    console.log('Datos del usuario antes de la inserción:', this.user2);
    this.userService.insertUser(this.user2).subscribe(() => {
      
    }, error => {
      console.log(error);
    });
  }
  
  assignProduct(event: any) {
    // Reiniciar la lista de productos seleccionados
    const selectedOptions = event.target.selectedOptions;
    console.log('Número de productos seleccionados:', selectedOptions.length);
    this.user2.products = [];
  
    for (let i = 0; i < selectedOptions.length; i++) {
      // Separar la cadena para obtener el ID del producto
      const parts = selectedOptions[i].value.split(':');
      if (parts.length === 2) {
        const productId = Number(parts[1].trim());
  
        console.log('ID del producto seleccionado:', productId);
  
        // Obtener el producto por su ID y agregarlo a la lista del usuario
        const selectedProduct = this.productList2.find(product => product.id === productId);
  
        console.log('Producto encontrado:', selectedProduct);
  
        if (selectedProduct) {
          // Agregar una copia del producto (evitar referencia directa)
          this.user2.products.push({ ...selectedProduct });
        } else {
          console.log(`No se encontró el producto con ID ${productId}.`);
        }
      } else {
        console.log('Formato incorrecto en la opción seleccionada:', selectedOptions[i].value);
      }
    }
  
    console.log('Productos seleccionados:', this.user2.products);
  }
  
  
}

