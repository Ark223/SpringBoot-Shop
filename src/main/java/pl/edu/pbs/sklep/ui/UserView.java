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
import pl.edu.pbs.sklep.ShopApplication;
import pl.edu.pbs.sklep.model.Product;
import pl.edu.pbs.sklep.repository.ProductRepository;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Route("/user")
@StyleSheet("/css/style.css")
public class UserView extends VerticalLayout {
    private static final long serialVersionUID = 1L;
    private VerticalLayout layout = new VerticalLayout();

    private UserView() {
        if (ShopApplication.loggedIn.equals(-1))
            UI.getCurrent().navigate(LoginView.class);
    }

    @Autowired
    ProductRepository productRepository;

    @PostConstruct
    private void init() {
        this.layout.setSizeFull();
        Button logout = new Button("Wyloguj się");
        logout.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        logout.addClickListener(e -> {
            ShopApplication.loggedIn = -1;
            UI.getCurrent().navigate(MainView.class);
        });
        Button cart = new Button("Zobacz koszyk");
        cart.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        this.layout.setHorizontalComponentAlignment(Alignment.END, cart);
        this.layout.setHorizontalComponentAlignment(Alignment.END, logout);
        List<Item> items = new ArrayList<>();
        List<Product> products = this.productRepository.findAll();
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

            });
            items.add(item);
        }
        Grid<Item> grid = new Grid<>(Item.class, false);
        grid.setMaxWidth(90f, Unit.PERCENTAGE);
        grid.setVerticalScrollingEnabled(true);
        grid.setHeight(300, Unit.PIXELS);
        grid.setHeightByRows(true);
        grid.addColumn(i -> i.name).setHeader("Nazwa produktu").setAutoWidth(true);
        grid.addColumn(i -> i.description).setHeader("Opis produktu").setAutoWidth(true);
        grid.addComponentColumn(i -> i.image).setHeader("Zdjęcie").setAutoWidth(true);
        grid.addColumn(i -> i.price).setHeader("Cena").setAutoWidth(true);
        grid.addComponentColumn(i -> i.button).setHeader("").setAutoWidth(true);
        grid.setItems(items);
        this.layout.add(logout, cart, grid);
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
