package LL.StockSystem.Controller;

import LL.StockSystem.Modelo.Producto;
import LL.StockSystem.Modelo.Usuario;
import LL.StockSystem.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    private ProductoService ps;
    @GetMapping("")
    public List<Producto> getAll(){
        return ps.getAll();
    }
    @PostMapping("")
    public ResponseEntity<Producto> save(){
      return null;
    }


    // http://localhost:8080/producto/insertarProducto
    @PostMapping("/insertarProducto")
    public ResponseEntity<String> insertarProducto(@RequestBody Producto producto) {
        return ps.insertarProducto(producto);
    }

    @PostMapping("/{id}/actualizarProducto")
    public ResponseEntity<String> actualizarProducto(@PathVariable Integer id, @RequestBody Producto producto){
        return ps.actualizarProducto(id,producto);

    }
    @PostMapping("/{id}/eliminarProducto")
    public ResponseEntity<String> eliminarProducto (@PathVariable Integer id) {

        return ps.eliminarProducto(id);
    }
    }
