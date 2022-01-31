package pl.edu.pbs.sklep.ui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pbs.sklep.ShopApplication;
import pl.edu.pbs.sklep.model.CartItem;
import pl.edu.pbs.sklep.model.Product;
import pl.edu.pbs.sklep.model.User;
import pl.edu.pbs.sklep.repository.CartItemRepository;
import pl.edu.pbs.sklep.repository.ProductRepository;
import pl.edu.pbs.sklep.repository.UserRepository;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Route("/user")
@PageTitle("Sklep komputerowy")
@StyleSheet("/css/style.css")
public class UserView extends VerticalLayout {
    private static final long serialVersionUID = 1L;
    private HorizontalLayout layout = new HorizontalLayout();
    private Button userInfo;
    private Button cart, logout;
    private Grid<Item> grid;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @PostConstruct
    private void init() {
        if (ShopApplication.loggedIn.equals(-1)) {
            UI.getCurrent().navigate(LoginView.class);
            return;
        }

        User user = userRepository.findById(ShopApplication.loggedIn).get();
        this.userInfo = new Button("Zalogowany jako: " + user.getUsername());

        this.cart = new Button("Zobacz koszyk");
        this.cart.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        this.cart.addClickListener(e -> UI.getCurrent().navigate(CartView.class));

        this.logout = new Button("Wyloguj się");
        this.logout.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        this.logout.addClickListener(e -> {
            ShopApplication.loggedIn = -1;
            UI.getCurrent().navigate(MainView.class);
        });

        List<Product> products = this.productRepository.findAll();
        List<Item> items = new ArrayList<>();
        for (Product product : products) {
            Item item = new Item();
            item.name = product.getName();
            item.category = product.getCategory();
            item.description = new Label(product.getDescription());
            item.description.setSizeUndefined();
            item.description.getStyle().set("line-break", "auto");
            item.image = new Image(product.getImageUrl(), "");
            item.image.setWidth(400, Unit.PIXELS);
            item.image.setHeight(200, Unit.PIXELS);
            item.price = product.getPrice().toString() + " zł";
            item.button = new Button("Dodaj do koszyka");
            item.button.addClickListener(e -> {
                Product intProduct = productRepository.findByName(item.name);
                CartItem cartItem = new CartItem(0, intProduct, user);
                cartItemRepository.save(cartItem);
                Notification.show("Dodano przedmiot do twojego koszyka");
            });
            items.add(item);
        }

        this.grid = new Grid<>(Item.class, false);
        this.grid.setVerticalScrollingEnabled(true);
        this.grid.setHeightByRows(true);
        this.grid.addColumn(i -> i.name).setHeader("Nazwa produktu").setAutoWidth(true);
        this.grid.addColumn(i -> i.category).setHeader("Kategoria").setAutoWidth(true);
        this.grid.addComponentColumn(i -> i.description).setHeader(
            "Opis produktu").setWidth("500px").setResizable(true);
        this.grid.addComponentColumn(i -> i.image).setHeader("Zdjęcie").setAutoWidth(true);
        this.grid.addColumn(i -> i.price).setHeader("Cena").setAutoWidth(true);
        this.grid.addComponentColumn(i -> i.button).setHeader("").setAutoWidth(true);
        this.grid.setItems(items);

        setSizeFull();
        this.layout.add(this.userInfo, this.cart, this.logout);
        setHorizontalComponentAlignment(Alignment.END, this.layout);
        add(this.layout, this.grid);
    }

    private class Item {
        public String name;
        public String category;
        public Label description;
        public Image image;
        public String price;
        public Button button;
    }
}
