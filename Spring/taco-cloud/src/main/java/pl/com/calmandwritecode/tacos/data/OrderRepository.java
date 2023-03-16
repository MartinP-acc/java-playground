package pl.com.calmandwritecode.tacos.data;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import pl.com.calmandwritecode.tacos.Order;
import pl.com.calmandwritecode.tacos.UserTaco;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order,Long> {

    List<Order> findByUserTacoOrderByPlacedAtDesc(UserTaco userTaco, Pageable pageable);
}
