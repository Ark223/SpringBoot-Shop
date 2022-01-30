package pl.edu.pbs.sklep.ui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import pl.edu.pbs.sklep.ShopApplication;

@Route("/user")
@StyleSheet("/css/style.css")
public class UserView extends VerticalLayout {
    private UserView() {
        if (ShopApplication.loggedIn.equals(-1))
            UI.getCurrent().navigate(LoginView.class);
    }
}
