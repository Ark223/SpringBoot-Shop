package pl.edu.pbs.sklep.ui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pbs.sklep.ShopApplication;
import pl.edu.pbs.sklep.model.Product;
import pl.edu.pbs.sklep.repository.CartItemRepository;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Route("/cart")
@StyleSheet("/css/style.css")
public class CartView extends VerticalLayout {
    private static final long serialVersionUID = 1L;
    private Button buy, logout;
    private Grid<Item> grid;

    @Autowired
    CartItemRepository cartItemRepository;

    @PostConstruct
    private void init() {
        if (ShopApplication.loggedIn.equals(-1)) {
            UI.getCurrent().navigate(LoginView.class);
            return;
        }

        this.buy = new Button("Wykup koszyk");
        this.buy.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        this.logout = new Button("Wyloguj się");
        this.logout.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        this.logout.addClickListener(e -> {
            ShopApplication.loggedIn = -1;
            UI.getCurrent().navigate(MainView.class);
        });

        List<Product> cart = this.cartItemRepository.findAll()
            .stream().filter(i -> i.getUserId().getId()
                    .equals(ShopApplication.loggedIn))
            .map(i -> i.getProductId())
            .collect(Collectors.toList());
        List<Item> items = new ArrayList<>();
        for (Product product : cart) {
            Item item = new Item();
            item.name = product.getName();
            item.category = product.getCategory();
            item.price = product.getPrice() + "zł";
            item.button = new Button("Usuń przedmiot");
            items.add(item);
        }

        this.grid = new Grid<>(Item.class, false);
        this.grid.setVerticalScrollingEnabled(true);
        this.grid.setHeightByRows(true);
        this.grid.setWidth("50%");
        this.grid.addColumn(i -> i.name).setHeader("Nazwa produktu").setAutoWidth(true);
        this.grid.addColumn(i -> i.category).setHeader("Kategoria").setAutoWidth(true);
        this.grid.addColumn(i -> i.price).setHeader("Cena").setAutoWidth(true);
        this.grid.addComponentColumn(i -> i.button).setHeader("Akcja").setAutoWidth(true);
        this.grid.setItems(items);

        setSizeFull();
        setHorizontalComponentAlignment(Alignment.END, this.logout);
        add(this.logout, this.grid, this.buy);
    }

    private class Item {
        public String name;
        public String category;
        public String price;
        public Button button;
    }
}
