package LL.StockSystem.Repository;

import LL.StockSystem.Modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Integer> {
    @Query(value = "select producto_id from usuario_producto where usuario_id = :usuarioId", nativeQuery = true)
    public List<Integer> getUsuarioProducto(Integer usuarioId);
}
