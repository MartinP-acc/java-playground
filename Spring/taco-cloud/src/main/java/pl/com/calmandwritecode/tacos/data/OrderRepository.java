package pl.com.calmandwritecode.tacos.data;

import org.springframework.data.repository.CrudRepository;
import pl.com.calmandwritecode.tacos.Order;

public interface OrderRepository extends CrudRepository<Order,Long> {
}
