package pl.com.calmandwritecode.tacos.data;

import pl.com.calmandwritecode.tacos.Order;

public interface OrderRepository {

    Order save(Order order);
}
