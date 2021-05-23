package order;

import notification.OrderObserver;

import java.util.HashSet;
import java.util.Set;

public class Order implements OrderObservable{

    private Long orderNumber;
    private OrderStatus orderStatus;
    private Set<OrderObserver> registeredObservers = new HashSet<>();

    public Order(Long orderNumber, OrderStatus orderStatus) {
        this.orderNumber = orderNumber;
        this.orderStatus = orderStatus;
    }

    @Override
    public void registerObserver(OrderObserver observer) {
        registeredObservers.add(observer);
    }

    @Override
    public void unregisterObserver(OrderObserver observer) {
        registeredObservers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (OrderObserver observer: registeredObservers)
            observer.updateOrderStatus(this);
    }

    public void changeOrderStatus(OrderStatus orderStatus){
        setOrderStatus(orderStatus);
        notifyObserver();
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
