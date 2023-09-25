package LL.StockSystem.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario {
    @Id
    private Integer id;
    private String name;
    private String nickName;
    private String password;
    private Date fechaNacimiento;
    private Integer edad;
    private byte[] imagen;
    @ManyToMany
    @JoinTable(name = "usuario_producto", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "producto_id"))
    private List<Producto> productos;
}
