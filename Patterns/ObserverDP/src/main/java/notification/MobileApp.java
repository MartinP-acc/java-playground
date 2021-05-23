package notification;

import order.Order;

public class MobileApp implements OrderObserver{

    public void updateOrderStatus(Order order){
        System.out.println("Mobile App - Zamówenie nr :"+order.getOrderNumber()+" zmieniło status :"+order.getOrderStatus());
    }
}
