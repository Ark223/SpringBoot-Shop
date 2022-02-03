package pl.edu.pbs.sklep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EntityScan("pl.edu.pbs.sklep.model")
@ComponentScan(basePackages={"pl.edu.pbs.sklep"})
public class ShopApplication {
    public static Map<String, Integer> loggedIn =
        new HashMap<String, Integer>();
    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }
}
