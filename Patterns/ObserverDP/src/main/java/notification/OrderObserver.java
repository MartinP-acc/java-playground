package notification;

import order.Order;

public interface OrderObserver {

    void updateOrderStatus(Order order);
}
