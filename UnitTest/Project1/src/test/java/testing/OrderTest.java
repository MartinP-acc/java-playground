package testing;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(BeforeAfterExtension.class)
class OrderTest {

    private Order order;

    @BeforeEach
    void initOrder(){
        this.order = new Order();
    }

    @AfterEach
    void clearOrderList(){
        this.order.cancel();
    }

    @Test
    void mealListShouldBeEmptyAfterCreationOrder(){

        //then
        assertThat(order.getMeals(),empty());
    }

    @Test
    void addingOrderShouldIncreaseOrderSize(){

        //given
        Meal meal = new Meal(30, "Classic Burger");

        //when
        order.addMealsToOrder(meal);

        //then
        assertThat(order.getMeals(), hasSize(1));
        assertThat(order.getMeals(),contains(meal));
        assertThat(order.getMeals().get(0).getPrice(), equalTo(30));
    }

    @Test
    void removingMealFromOrderShouldDecreaseOrderSize(){
        //given
        Meal meal = new Meal(30, "Classic Burger");

        //when
        order.addMealsToOrder(meal);
        order.removeMealFromOrder(meal);

        //then
        assertThat(order.getMeals(),empty());
        assertThat(order.getMeals(),not(contains(meal)));
    }

    @Test
    void mealsShouldBeInCorrectOrderAfterAddingMealsToOrderList(){
        //given
        Meal meal1 = new Meal(30, "Classic Burger");
        Meal meal2 = new Meal(35, "Cheese and Bacon Burger");


        //when
        order.addMealsToOrder(meal1);
        order.addMealsToOrder(meal2);

        //then
        assertThat(order.getMeals(), containsInAnyOrder(meal2,meal1));
        assertThat(order.getMeals(), contains(meal1,meal2));
    }

    @Test
    void testIfTwoMealListsAreTheSame(){
        //given
        Meal meal1 = new Meal(30, "Classic Burger");
        Meal meal2 = new Meal(35, "Cheese and Bacon Burger");
        Meal meal3 = new Meal(29, "Chicken Burger");

        List<Meal> meals = Arrays.asList(meal1,meal2,meal3);

        //when
        order.addMealsToOrder(meal1);
        order.addMealsToOrder(meal2);
        order.addMealsToOrder(meal3);

        //then
        assertThat(order.getMeals(), is(meals));
    }


}