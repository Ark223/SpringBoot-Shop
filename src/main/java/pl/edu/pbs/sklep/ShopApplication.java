package pl.edu.pbs.sklep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@EntityScan("pl.edu.pbs.sklep.model.entities")
@ComponentScan({"pl.edu.pbs.sklep.repository", "pl.edu.pbs.sklep.service", "pl.edu.pbs.sklep.controller"})
public class ShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }
}
