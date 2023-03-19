package pl.com.calmandwritecode.tacos.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import pl.com.calmandwritecode.tacos.Order;
import pl.com.calmandwritecode.tacos.UserTaco;
import pl.com.calmandwritecode.tacos.data.OrderRepository;


@Controller
@RequestMapping("/order")
@SessionAttributes("order")
public class OrderController {

    private OrderRepository orderRepository;
    private OrdersProps ordersProps;

    @Autowired
    public OrderController(
            OrderRepository orderRepository,
            OrdersProps ordersProps){
        this.orderRepository = orderRepository;
        this.ordersProps = ordersProps;
    }

    @GetMapping("/current")
    public String orderForm(){
        return "orderForm";
    }

    @GetMapping
    public String ordersForUser(
            @AuthenticationPrincipal UserTaco userTaco,
            Model model){
        Pageable pageable = PageRequest.of(0, ordersProps.getPageSize());
        model.addAttribute("orders",
                orderRepository.findByUserTacoOrderByPlacedAtDesc(userTaco, pageable));

        return "orderList";
    }

    @PostMapping
    public String processOrder(
            @Valid Order order,
            Errors errors,
            SessionStatus sessionStatus){
        if (errors.hasErrors()){
            return "orderForm";
        }else {

            orderRepository.save(order);
            sessionStatus.setComplete();
            return "redirect:/";
        }
    }
}
