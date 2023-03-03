package pl.com.calmandwritecode.tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.com.calmandwritecode.tacos.Ingredient;
import pl.com.calmandwritecode.tacos.Taco;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    @GetMapping
    public String showDesignForm(Model model){
        List<Ingredient> ingredient = Arrays.asList(
                new Ingredient("FLTO", "wheat", Ingredient.Type.WRAP),
                new Ingredient("COTO", "corn", Ingredient.Type.WRAP),
                new Ingredient("GRBF", "ground beef", Ingredient.Type.PROTEIN),
                new Ingredient("CARN", "piece of meat", Ingredient.Type.PROTEIN),
                new Ingredient("TMTO", "tomato cubes", Ingredient.Type.VEGGIES),
                new Ingredient("LETC", "lettuce", Ingredient.Type.VEGGIES),
                new Ingredient("CHED", "cheddar", Ingredient.Type.CHEESE),
                new Ingredient("JACK", "monterey Jack", Ingredient.Type.CHEESE),
                new Ingredient("SLSA", "spice tomato sauce", Ingredient.Type.SAUCE),
                new Ingredient("SRCR", "cream", Ingredient.Type.SAUCE)
        );

        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types){
            model.addAttribute(type.filterByType(ingredient,type));
        }
        //model.addAttribute("design", new Taco());
        return "design";
    }
}
