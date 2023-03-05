package pl.com.calmandwritecode.tacos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class Taco {

    @NotNull
    @Size(min = 5, message = "Name must have at least 5 digits")
    private String name;
    @NotNull(message = "You must select at least one ingredient")
    private List<String> ingredients;
}
