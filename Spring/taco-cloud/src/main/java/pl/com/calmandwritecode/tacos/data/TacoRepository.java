package pl.com.calmandwritecode.tacos.data;

import org.springframework.data.repository.CrudRepository;
import pl.com.calmandwritecode.tacos.Taco;

public interface TacoRepository extends CrudRepository<Taco,Long> {
}
