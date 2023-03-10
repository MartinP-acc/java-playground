package pl.com.calmandwritecode.tacos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.com.calmandwritecode.tacos.data.IngredientRepository;

@SpringBootApplication
public class TacoCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(IngredientRepository repo) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				repo.save(new Ingredient("FLTO", "flour taco", Ingredient.Type.WRAP));
				repo.save(new Ingredient("COTO", "corn taco", Ingredient.Type.WRAP));
				repo.save(new Ingredient("GRBF", "beef", Ingredient.Type.PROTEIN));
				repo.save(new Ingredient("CARN", "peaces of meat", Ingredient.Type.PROTEIN));
				repo.save(new Ingredient("TMTO", "tomato cubes", Ingredient.Type.VEGGIES));
				repo.save(new Ingredient("LETC", "salad", Ingredient.Type.VEGGIES));
				repo.save(new Ingredient("CHED", "cheddar", Ingredient.Type.CHEESE));
				repo.save(new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE));
				repo.save(new Ingredient("SLSA", "spice tomato sauce", Ingredient.Type.SAUCE));
				repo.save(new Ingredient("SRCR", "cream", Ingredient.Type.SAUCE));
			}
		};
	}

}
