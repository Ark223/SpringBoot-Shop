package pl.edu.pbs.sklep.ui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pbs.sklep.model.Product;
import pl.edu.pbs.sklep.repository.ProductRepository;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Route("/")
@StyleSheet("/css/style.css")
public class MainView extends VerticalLayout {
    private static final long serialVersionUID = 1L;
    private VerticalLayout layout = new VerticalLayout();

    @Autowired
    ProductRepository productRepository;

    @PostConstruct
    private void init() {
        this.layout.setSizeFull();
        Button register = new Button("Zarejestruj się");
        register.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Button login = new Button("Zaloguj się");
        login.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        login.addClickListener(e -> UI.getCurrent().navigate(LoginView.class));
        this.layout.setHorizontalComponentAlignment(Alignment.END, login);
        this.layout.setHorizontalComponentAlignment(Alignment.END, register);
        List<Product> products = this.productRepository.findAll();
        List<Item> items = new ArrayList<>();
        for (Product product : products) {
            Item item = new Item();
            item.name = product.getName();
            item.description = product.getDescription();
            item.image = new Image(product.getImageUrl(), "");
            item.image.setWidth(400, Unit.PIXELS);
            item.image.setHeight(200, Unit.PIXELS);
            item.price = product.getPrice().toString() + " zł";
            item.button = new Button("Dodaj do koszyka");
            item.button.addClickListener(e -> {
                UI.getCurrent().navigate(LoginView.class);
            });
            items.add(item);
        }
        Grid<Item> grid = new Grid<>(Item.class, false);
        grid.setVerticalScrollingEnabled(true);
        grid.setHeight(300, Unit.PIXELS);
        grid.setHeightByRows(true);
        grid.addColumn(i -> i.name).setHeader("Nazwa produktu").setAutoWidth(true);
        grid.addColumn(i -> i.description).setHeader("Opis produktu").setAutoWidth(true);
        grid.addComponentColumn(i -> i.image).setHeader("Zdjęcie").setAutoWidth(true);
        grid.addColumn(i -> i.price).setHeader("Cena").setAutoWidth(true);
        grid.addComponentColumn(i -> i.button).setHeader("").setAutoWidth(true);
        grid.setItems(items);
        this.layout.add(login, register, grid);
        add(this.layout);
    }

    private class Item {
        public String name;
        public String description;
        public Image image;
        public String price;
        public Button button;
    }
}
