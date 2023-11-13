package LL.StockSystem.Service;
import LL.StockSystem.Model.Product;
import LL.StockSystem.Model.ProductDTO;
import LL.StockSystem.Model.User;
import LL.StockSystem.Repository.ProductRepository;
import LL.StockSystem.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Service
public class UserService {

    @Autowired
    private UserRepository ur;
    @Autowired
    private ProductRepository pr;
    private final ModelMapper mm = new ModelMapper();
    public List<User> getAll(){
        return ur.findAll();
    }
    public UserService(UserRepository ur){
        this.ur = ur;
    }

    public ResponseEntity insertUser(User user) {
        try {
            List<Product> products = user.getProducts();
            if (products != null && !products.isEmpty()) {
                for (Product product : products) {
                    // Verificar si el producto ya existe en la base de datos
                    Product existingProduct = pr.findByName(product.getName());

                    if (existingProduct != null) {
                        // Si el producto ya existe, usar el existente
                        product = existingProduct;
                    } else {
                        // Si el producto no existe, guardarlo
                        pr.save(product);
                    }
                }
            }
            ur.save(user);
            return ResponseEntity.status(CREATED).build();
        } catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity deleteUser(Integer id){
        try{
            ur.deleteById(id);
            return ResponseEntity.status(OK).build();
        }catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity updateUser(Integer id,User usuario){
        try {
            User usr = ur.findById(id).get();
            //usr.setId(usuario.getId());
            usr.setName(usuario.getName());
            usr.setNickName(usuario.getNickName());
            usr.setPassword(usuario.getPassword());
            usr.setBirthDate(usuario.getBirthDate());
            usr.setAge(usuario.getAge());
            usr.setImage(usuario.getImage());
            usr.setProducts(usuario.getProducts());
            ur.save(usr);
            return ResponseEntity.status(OK).build();
        }catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
    public List<ProductDTO> getUserProduct(Integer user_id){
        List<ProductDTO> ProductList = new ArrayList<>();
        List<Integer> UserProduct = ur.getUserProduct(user_id);
        for(Integer pid : UserProduct){
            Product product = pr.findById(pid).orElseThrow(() -> new HttpClientErrorException(BAD_REQUEST));
            ProductList.add(mm.map(product, ProductDTO.class));
        }
        return ProductList;
    }


}
