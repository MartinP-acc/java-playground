package pl.com.calmandwritecode.tacos.data;

import org.springframework.data.repository.CrudRepository;
import pl.com.calmandwritecode.tacos.UserTaco;

public interface UserRepository extends CrudRepository<UserTaco, Long> {

    UserTaco findByUsername(String username);
}
