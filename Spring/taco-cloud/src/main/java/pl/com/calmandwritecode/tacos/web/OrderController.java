package pl.com.calmandwritecode.tacos.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.com.calmandwritecode.tacos.Order;

@Slf4j
@Controller
@RequestMapping("/order")
public class OrderController {

    @GetMapping("/current")
    public String order(Model model){
        model.addAttribute("order", new Order());
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors){
        if (errors.hasErrors()){
            return "orderForm";
        }else {
            log.info("Order has been submit :" + order);
            return "redirect:/";
        }
    }
}
