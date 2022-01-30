package pl.edu.pbs.sklep.ui;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pbs.sklep.ShopApplication;
import pl.edu.pbs.sklep.model.User;
import pl.edu.pbs.sklep.repository.UserRepository;
import java.util.Optional;

@Route("/login")
@StyleSheet("/css/style.css")
public class LoginView extends Composite<LoginOverlay> {
    private LoginOverlay loginOverlay;

    @Autowired
    UserRepository userRepository;

    public LoginView() {
        this.loginOverlay = getContent();
        this.loginOverlay.setTitle("Logowanie");
        this.loginOverlay.setDescription("Sklep komputerowy");
        this.loginOverlay.setOpened(true);

        this.loginOverlay.addLoginListener(event -> {
            Optional<User> user = userRepository.findAll().stream()
                .filter(u -> u.getUsername().equals(event.getUsername()) &&
                u.getPassword().equals(event.getPassword())).findFirst();
            if (user.isPresent()) {
                ShopApplication.loggedIn = user.get().getId();
                UI.getCurrent().navigate(UserView.class);
            }
            else this.loginOverlay.setError(true);
        });
    }
}
