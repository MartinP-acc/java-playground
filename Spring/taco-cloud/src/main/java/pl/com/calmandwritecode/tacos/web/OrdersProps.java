package pl.com.calmandwritecode.tacos.web;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties(prefix = "taco.orders")
@Data
@Validated
public class OrdersProps {
    @Min(value = 5, message = "value must be between 5 and 25")
    @Max(value = 25, message = "value must be between 5 and 25")
    private int pageSize = 20;
}
