package testing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

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

    @Test
    void exceptionShouldBeThrownIfDiscountIsHigherThanPrice(){

        //given
        Meal meal1 = new Meal(10,"Pizza");

        //then
        assertThrows(IllegalStateException.class, () -> meal1.getDiscountedPrice(11));

    }

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 15, 18})
    void mealShouldBeLowerThan20(int price){
        assertThat(price).isLessThan(20);
    }

    private static Stream<Arguments> createMealsWithNameAndPrice(){
        return Stream.of(
                Arguments.of("Classic Burger", 30),
                Arguments.of("Cheese and Bacon Burger", 35),
                Arguments.of("Chicken Burger", 28),
                Arguments.of("Cheap Burger", 25)
        );
    }

    @ParameterizedTest
    @MethodSource("createMealsWithNameAndPrice")
    void orderShouldHaveCorrectNameAndPrice(String name, int price){
        assertThat(name).contains("Burger");
        assertThat(price).isLessThan(36);
    }

    private static Stream<String> createCakeNames(){
        List<String> cakeNames = Arrays.asList("cheesecake", "fruitcake", "cupcake");
        return cakeNames.stream();
    }

    @ParameterizedTest
    @MethodSource("createCakeNames")
    void cakeNamesShouldEndWithCake(String name){
        assertThat(name).endsWith("cake");
    }
}