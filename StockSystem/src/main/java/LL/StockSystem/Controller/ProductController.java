package LL.StockSystem.Controller;

import LL.StockSystem.Model.Product;
import LL.StockSystem.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/product")
//http://localhost:8080/product

public class ProductController {
    @Autowired
    private ProductService ps;
    @GetMapping("")
    public List<Product> getAll(){
        return ps.getAll();
    }

    @GetMapping("/{id}/getById")
    public Optional<Product> getById(@PathVariable Integer id){
        return ps.getById(id);
    }



    // http://localhost:8080/producto/insertarProducto
    @PostMapping("/insertProduct")
    public ResponseEntity<String> insertProduct(@RequestBody Product product) {
        return ps.insertProduct(product);
    }

    @PostMapping("/{id}/updateProduct")
    public ResponseEntity<String> updateProduct(@PathVariable Integer id, @RequestBody Product product){
        return ps.updateProduct(id,product);

    }
    @PostMapping("/{id}/deleteProduct")
    public ResponseEntity<String> deleteProduct (@PathVariable Integer id) {
        return ps.deleteProduct(id);
    }
    }