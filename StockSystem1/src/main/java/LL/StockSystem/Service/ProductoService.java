package LL.StockSystem.Service;
import LL.StockSystem.Modelo.Producto;
import LL.StockSystem.Modelo.Usuario;
import LL.StockSystem.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.ProcessingInstruction;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;


@Service
public class ProductoService{
    @Autowired
    private ProductoRepository pr;
    public List<Producto> getAll(){
        return pr.findAll();
    }
    public ResponseEntity<Producto> save(){
        return null;
    }
    public ProductoService(ProductoRepository pr){
        this.pr = pr;
    }
    public ResponseEntity insertarProducto(Producto producto) {
        try{
            pr.save(producto);
            return ResponseEntity.status(CREATED).build();
        } catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
    public ResponseEntity eliminarProducto(Integer id){
        try {
                pr.deleteById(id);
                return ResponseEntity.status(OK).build();
        }catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
    public ResponseEntity actualizarProducto(Integer id, Producto producto){

        try {
            Producto pro = pr.findById(id).get();

            pro.setName(producto.getName());
            pro.setPrecio(producto.getPrecio());
            pro.setImagen(producto.getImagen());
            pro.setId(producto.getId());
            pro.setDescripcion(producto.getDescripcion());
            pro.setUsuario(producto.getUsuario());
            pr.save(pro);
            return ResponseEntity.status(OK).build();

        }catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

}

