package LL.StockSystem.Controller;

import LL.StockSystem.Modelo.ProductoDTO;
import LL.StockSystem.Modelo.Usuario;
import LL.StockSystem.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService us;
    @GetMapping("")
    public List<Usuario> getAll(){
        return us.getAll();
    }
    // http://localhost:8080/producto/insertarUsuario
    @PostMapping("/insertarUsuario")
    public ResponseEntity<String> insertarUsuario(@RequestBody Usuario usuario) {
        return us.insertarUsuario(usuario);
    }
    // http://localhost:8080/producto/actualizarUsuario
    @PostMapping("/{id}/actualizarUsuario")
    public ResponseEntity<String> actualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuario){
        return us.actualizarUsuario(id,usuario);
    }
    // http://localhost:8080/producto/eliminarUsuario
    @PostMapping("/{id}/eliminarUsuario")
    public ResponseEntity<String> eliminarUsuario (@PathVariable Integer id) {
        return us.eliminarUsuario(id);
    }

    @PostMapping("/{usuarioId}/usuarioProducto")
    public List<ProductoDTO> getUsuarioProducto(@PathVariable Integer usuarioId){
        return us.getUsuarioProducto(usuarioId);
    }

}
