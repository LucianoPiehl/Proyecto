package LL.StockSystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import LL.StockSystem.Model.User;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer stock;
    private Integer price;
    private String description;
    private byte[] image;
    @ManyToMany(mappedBy = "products")
    @JsonIgnore
    private List<User> user;
}
