package LL.StockSystem.Controller;

import LL.StockSystem.Model.ProductDTO;
import LL.StockSystem.Model.User;
import LL.StockSystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService us;
    @GetMapping("")
    public List<User> getAll(){
        return us.getAll();
    }
    // http://localhost:8080/user/insertUser
    @PostMapping("/insertUser")
    public ResponseEntity<String> insertUser(@RequestBody User user) {
        return us.insertUser(user);
    }
    // http://localhost:8080/user/updateUser
    @PostMapping("/{id}/updateUser")
    public ResponseEntity<String> updateUser(@PathVariable Integer id, @RequestBody User user){
        return us.updateUser(id,user);
    }
    // http://localhost:8080/user/deleteUser
    @PostMapping("/{id}/deleteUser")
    public ResponseEntity<String> deleteUser (@PathVariable Integer id) {
        return us.deleteUser(id);
    }

    @PostMapping("/{usuarioId}/userProduct")
    public List<ProductDTO> getUserProduct(@PathVariable Integer userId){
        return us.getUserProduct(userId);
    }

}
