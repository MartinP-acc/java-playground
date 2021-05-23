package notification;

import order.Order;

public class TextMsg implements OrderObserver{

    public void updateOrderStatus(Order order){
        System.out.println("SMS - Zamówenie nr :"+order.getOrderNumber()+" zmieniło status :"+order.getOrderStatus());
    }
}
