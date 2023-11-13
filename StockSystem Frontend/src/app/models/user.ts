import { Product } from "./product";

export class User {
    id: number
    name: string
    nickName: string
    password: string   
    products: Array<Product>
    
}