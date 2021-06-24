package testing.order;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import testing.Meal;
import testing.Order;
import testing.order.OrderBackup;

import java.io.IOException;

class OrderBackupTest {

    private static OrderBackup orderBackup;

    @BeforeAll
    static void setup() throws IOException{
        orderBackup = new OrderBackup();
        orderBackup.createFile();
    }

    @Test
    void backupOrderWithOneMeal() throws IOException{

        //given
        Meal meal = new Meal(7, "Fries");
        Order order = new Order();
        order.addMealsToOrder(meal);

        //when
        orderBackup.backupWriter(order);

        //then
        System.out.println("Order: "+ order.toString()+" backed up");

    }

    @AfterAll
    static void tearDown() throws IOException {
        orderBackup.closeWriter();
    }

}