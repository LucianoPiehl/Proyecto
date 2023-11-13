package LL.StockSystem.Repository;

import LL.StockSystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository  extends JpaRepository<User, Integer> {
    @Query(value = "select product_id from user_product where user_id = :userId", nativeQuery = true)
    public List<Integer> getUserProduct(Integer userId);
}
