package pl.com.calmandwritecode.tacos.security;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.com.calmandwritecode.tacos.UserTaco;
@Data
public class RegistrationForm {

    private String username;
    private String password;
    private String name;
    private String street;
    private String city;
    private String region;
    private String zip;
    private String phone;

    public UserTaco toUser(PasswordEncoder passwordEncoder){
        return new UserTaco(
                username,
                passwordEncoder.encode(password),
                name,
                street,
                city,
                region,
                zip,
                phone
        );
    }
}
