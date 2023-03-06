package pl.com.calmandwritecode.tacos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
public class Ingredient {

    private final String id;
    private final String name;
    private final Type type;

    public static enum Type{
        WRAP,
        PROTEIN,
        VEGGIES,
        CHEESE,
        SAUCE;

        public List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {

            return ingredients.stream()
                    .filter(x -> x.getType().equals(type))
                    .collect(Collectors.toList());

        }
    }


}