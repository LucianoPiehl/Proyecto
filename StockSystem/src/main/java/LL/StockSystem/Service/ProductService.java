package LL.StockSystem.Service;
import LL.StockSystem.Model.Product;
import LL.StockSystem.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;


@Service
public class ProductService{
    @Autowired
    private ProductRepository pr;
    public List<Product> getAll(){
        return pr.findAll();
    }
    public ResponseEntity<Product> save(){
        return null;
    }
    public ProductService(ProductRepository pr){
        this.pr = pr;
    }
    public ResponseEntity insertProduct(Product product) {
        try{
            pr.save(product);
            return ResponseEntity.status(CREATED).build();
        } catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
    public ResponseEntity deleteProduct(Integer id){
        try {
            pr.deleteById(id);
            return ResponseEntity.status(OK).build();
        }catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
    public Optional<Product> getById(Integer id){
        try{
            Optional<Product> product = pr.findById(id);
            return product;
        } catch(Exception e){
            return null;
        }
    }
    public ResponseEntity updateProduct(Integer id, Product product){

        try {
            Product pro = pr.findById(id).get();
            //pro.setId(product.getId());
            pro.setName(product.getName());
            pro.setPrice(product.getPrice());
            pro.setImage(product.getImage());
            pro.setDescription(product.getDescription());
            pro.setUser(product.getUser());
            pr.save(pro);
            return ResponseEntity.status(OK).build();

        }catch (Exception e){
            System.out.println(ResponseEntity.status(INTERNAL_SERVER_ERROR).build());
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

}

