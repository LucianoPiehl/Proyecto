package LL.StockSystem.Service;
import LL.StockSystem.Modelo.Producto;
import LL.StockSystem.Modelo.ProductoDTO;
import LL.StockSystem.Modelo.Usuario;
import LL.StockSystem.Repository.ProductoRepository;
import LL.StockSystem.Repository.UsuarioRepository;
import org.hibernate.sql.Delete;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository ur;
    @Autowired
    private ProductoRepository pr;
    private final ModelMapper mm = new ModelMapper();
    public List<Usuario> getAll(){
        return ur.findAll();
    }
    public UsuarioService(UsuarioRepository ur){
        this.ur = ur;
    }

    public ResponseEntity insertarUsuario(Usuario usuario) {
        try {
            ur.save(usuario);
            return ResponseEntity.status(CREATED).build();
        } catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity eliminarUsuario(Integer id){
        try{
        ur.deleteById(id);
        return ResponseEntity.status(OK).build();
        }catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity actualizarUsuario(Integer id,Usuario usuario){
        try {
            Usuario usr = ur.findById(id).get();

        usr.setId(usuario.getId());
        usr.setName(usuario.getName());
        usr.setNickName(usuario.getNickName());
        usr.setPassword(usuario.getPassword());
        usr.setFechaNacimiento(usuario.getFechaNacimiento());
        usr.setEdad(usuario.getEdad());
        usr.setImagen(usuario.getImagen());
        usr.setProductos(usuario.getProductos());
        ur.save(usr);
        return ResponseEntity.status(OK).build();
        }catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
    public List<ProductoDTO> getUsuarioProducto(Integer usuarioId){
        List<ProductoDTO> ProductoList = new ArrayList<>();
        List<Integer> UsuarioProducto = ur.getUsuarioProducto(usuarioId);
        for(Integer pid : UsuarioProducto){
            Producto producto = pr.findById(pid).orElseThrow(() -> new HttpClientErrorException(BAD_REQUEST));
            ProductoList.add(mm.map(producto, ProductoDTO.class));
        }
        return ProductoList;
    }


}
