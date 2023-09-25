package LL.StockSystem.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Producto{
    @Id
    private Integer id;
    private String name;
    private Integer stock;
    private Integer precio;
    private String descripcion;
    private byte[] imagen;
    @ManyToMany(mappedBy = "productos")
    @JsonIgnore
    private List<Usuario> usuario;
}
