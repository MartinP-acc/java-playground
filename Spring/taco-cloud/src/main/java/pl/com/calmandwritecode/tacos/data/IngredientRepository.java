package pl.com.calmandwritecode.tacos.data;

import pl.com.calmandwritecode.tacos.Ingredient;

public interface IngredientRepository {
    Iterable<Ingredient> findAll();
    Ingredient findById(String id);
    Ingredient save(Ingredient ingredient);
}
