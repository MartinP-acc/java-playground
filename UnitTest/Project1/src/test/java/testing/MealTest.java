package testing;

import org.junit.jupiter.api.Test;

//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class MealTest {

    @Test
    void shouldReturnDiscountedPrice() {

        //given
        Meal meal = new Meal(35);

        //when
        int discountedPrice = meal.getDiscountedPrice(7);

        //then
        assertEquals(28,discountedPrice);

        //matcher hamcrest
        //assertThat(discountedPrice, equalTo(28));

        //matcher assertJ
        assertThat(discountedPrice).isEqualTo(28);
    }

    @Test
    void referencesToTheSameObjectShouldBeEqual(){

        //given
        Meal meal1 = new Meal(10);
        Meal meal2 = meal1;
        Meal meal3 = new Meal(20);

        //then
        assertSame(meal1,meal2);
        assertNotSame(meal1,meal3);

        //matcher hamcrest
        //assertThat(meal2, sameInstance(meal1));
        //assertThat(meal3, not(sameInstance(meal2)));

        //matcher assertJ
        assertThat(meal2).isSameAs(meal1);
        assertThat(meal2).isNotSameAs(meal3);
    }

    @Test
    void twoMealsShouldBeEqualWhenPriceAndNameAreTheSame(){

        //given
        Meal meal1 = new Meal(10,"Pizza");
        Meal meal2 = new Meal(10,"Pizza");

        //then
        assertEquals(meal1,meal2);
    }
}