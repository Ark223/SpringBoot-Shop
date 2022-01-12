package pl.edu.pbs.sklep.ui;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pbs.sklep.model.Product;
import pl.edu.pbs.sklep.repository.ProductRepository;
import javax.annotation.PostConstruct;
import java.util.List;

@Route("/")
@StyleSheet("/css/style.css")
public class MainView extends VerticalLayout {
    private static final long serialVersionUID = 1L;

    @Autowired
    ProductRepository productRepository;

    @PostConstruct
    private void init() {
        List<Product> products = this.productRepository.findAll();
        for (Product product : products) {
            Label name = new Label(product.getName() + "\n");
            name.setHeight(5.0f, Unit.PERCENTAGE);
            Label desc = new Label(product.getDescription());
            desc.setHeight(5.0f, Unit.PERCENTAGE);
            Image image = new Image(product.getImageUrl(), "");
            image.setHeight(30.0f, Unit.PERCENTAGE);
            image.setWidth(30.0f, Unit.PERCENTAGE);
            image.setVisible(true);
            Label price = new Label(product.getPrice().toString() + " zł");
            price.setHeight(5.0f, Unit.PERCENTAGE);
            add(name, desc, image, price);
        }
    }
}
