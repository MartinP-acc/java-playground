package pl.mp;

import notification.Email;
import notification.MobileApp;
import notification.TextMsg;
import order.Order;
import order.OrderStatus;

public class Main {

    public static void main(String[] args) {

        Order order = new Order(5432L, OrderStatus.ZAREJESTROWANA);

        TextMsg textMsg = new TextMsg();
        Email email = new Email();
        MobileApp mobileApp = new MobileApp();

        order.registerObserver(textMsg);
        order.registerObserver(email);
        order.registerObserver(mobileApp);

        order.notifyObserver();

        order.changeOrderStatus(OrderStatus.WYSLANA);

        order.unregisterObserver(textMsg);

        order.changeOrderStatus(OrderStatus.ODEBRANA);

    }
}
