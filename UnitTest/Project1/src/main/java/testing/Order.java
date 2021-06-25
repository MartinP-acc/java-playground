package testing;

import testing.order.OrderStatus;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Meal> meals = new ArrayList<>();

    private OrderStatus orderStatus;

    public void addMealsToOrder(Meal meal){
        this.meals.add(meal);
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void changeOrderStatus(OrderStatus orderStatus){
        this.orderStatus = orderStatus;
    }

    public void removeMealFromOrder(Meal meal){
        this.meals.remove(meal);
    }

    public List<Meal> getMeals() {
        return meals;
    }

    void cancel(){
        this.meals.clear();
    }

    @Override
    public String toString() {
        return "Order{" +
                "meals=" + meals +
                '}';
    }
}
