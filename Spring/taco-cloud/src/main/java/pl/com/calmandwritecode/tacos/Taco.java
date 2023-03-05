package pl.com.calmandwritecode.tacos;

import jakarta.annotation.Nonnull;
import lombok.Data;

import java.util.List;

@Data
public class Taco {

    @Nonnull
    private String name;
    private List<String> ingredients;
}
