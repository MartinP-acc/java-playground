package pl.com.calmandwritecode.tacos.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pl.com.calmandwritecode.tacos.Ingredient;
import pl.com.calmandwritecode.tacos.Order;
import pl.com.calmandwritecode.tacos.Taco;
import pl.com.calmandwritecode.tacos.data.IngredientRepository;
import pl.com.calmandwritecode.tacos.data.TacoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;
    private final TacoRepository tacoRepo;

    @Autowired
    public DesignTacoController(
            IngredientRepository ingredientRepo,
            TacoRepository tacoRepository){
        this.ingredientRepo = ingredientRepo;
        this.tacoRepo = tacoRepository;
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @ModelAttribute(name = "order")
    public Order order(){
        return new Order();
    }

    @GetMapping
    public String showDesignForm(Model model){
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(ingredient -> ingredients.add(ingredient));

        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
        return "design";
    }

    private List<Ingredient> filterByType(
            List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @PostMapping
    public String processDesign(
            @Valid Taco taco,
            Errors errors,
            @ModelAttribute Order order){
        if (errors.hasErrors()){
            log.error(errors.toString());
            return "design";
        }else {
            Taco savedTaco = tacoRepo.save(taco);
            order.addTacoDesign(savedTaco);
            return "redirect:/order/current";
        }
    }
}
