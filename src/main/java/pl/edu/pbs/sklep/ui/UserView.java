package pl.edu.pbs.sklep.ui;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("/user")
@StyleSheet("/css/style.css")
public class UserView extends VerticalLayout {
    private UserView() {
        add(new H1("Witaj w sklepie!"));
    }
}
