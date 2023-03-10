package pl.com.calmandwritecode.tacos.data;

import org.springframework.data.repository.CrudRepository;
import pl.com.calmandwritecode.tacos.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
