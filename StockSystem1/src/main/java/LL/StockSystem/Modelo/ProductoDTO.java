package LL.StockSystem.Modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {
    private Integer id;
    private String name;
    private Integer stock;
    private Integer precio;
    private String descripcion;
    private byte[] imagen;
}
