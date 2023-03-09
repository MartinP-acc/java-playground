package pl.com.calmandwritecode.tacos.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pl.com.calmandwritecode.tacos.Ingredient;
import pl.com.calmandwritecode.tacos.Taco;
import pl.com.calmandwritecode.tacos.data.IngredientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo){
        this.ingredientRepo = ingredientRepo;
    }

    /*
    @ModelAttribute
    public void addIngredientsToModel(Model model) {
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
    }
     */

    private List<Ingredient> filterByType(
            List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model){
        List<Ingredient> ingredient = new ArrayList<>();
        ingredientRepo.findAll().forEach(ingredient::add);

        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredient, type));
        }
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco taco, Errors errors){
        if (errors.hasErrors()){
            return "design";
        }else {
            log.info("Processing taco design : " + taco);
            return "redirect:/order/current";
        }
    }
}
