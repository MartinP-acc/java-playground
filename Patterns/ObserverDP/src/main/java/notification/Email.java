package notification;

import order.Order;

public class Email implements OrderObserver {

    public void updateOrderStatus(Order order){
        System.out.println("Email - Zamówenie nr :"+order.getOrderNumber()+" zmieniło status :"+order.getOrderStatus());
    }
}
