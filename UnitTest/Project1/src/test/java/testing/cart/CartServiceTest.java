package testing.cart;

import org.junit.jupiter.api.Test;
import testing.Order;
import testing.order.OrderStatus;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

class CartServiceTest {

    @Test
    void processCartShouldSendToPrepare(){

        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(cart)).willReturn(true);

        //when
        Cart resultCart = cartService.processCart(cart);

        //then
        then(cartHandler).should().sendToPrepare(cart);

        assertThat(resultCart.getOrders().size()).isEqualTo(1);
        assertThat(resultCart.getOrders().get(0).getOrderStatus()).isEqualTo(OrderStatus.PREPARING);
    }

    @Test
    void processCartShouldNotSendToPrepare(){

        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(cart)).willReturn(false);

        //when
        Cart resultCart = cartService.processCart(cart);

        //then
        verify(cartHandler, never()).sendToPrepare(cart);
        then(cartHandler).should(never()).sendToPrepare(cart);

        assertThat(resultCart.getOrders().size()).isEqualTo(1);
        assertThat(resultCart.getOrders().get(0).getOrderStatus()).isEqualTo(OrderStatus.REJECTED);
    }

    @Test
    void canHandleCartShouldThrowException(){

        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(cart)).willThrow(IllegalStateException.class);

        //when
        Cart resultCart = cartService.processCart(cart);

        //then
        assertThrows(IllegalStateException.class, ()-> cartService.processCart(cart));
    }
}