package pl.edu.pbs.sklep.ui;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pbs.sklep.repository.UserRepository;

@Route("/login")
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
            if (userRepository.findAll().stream().anyMatch(u ->
                u.getUsername().equals(event.getUsername()) &&
                u.getPassword().equals(event.getPassword())))
                UI.getCurrent().navigate(UserView.class);
            else this.loginOverlay.setError(true);
        });
    }
}
