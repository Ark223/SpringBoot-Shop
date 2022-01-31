package pl.edu.pbs.sklep.ui;

import com.vaadin.flow.component.UI;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pbs.sklep.model.User;
import pl.edu.pbs.sklep.repository.UserRepository;
import java.time.LocalDateTime;

public class RegisterBind {
    private RegisterForm form;

    @Autowired
    UserRepository userRepository;

    public RegisterBind(RegisterForm form) {
        this.form = form;
    }

    public void validateAccount() {
        this.form.getSubmitButton().addClickListener(e -> {
            String user = this.form.getUsername().getValue();
            String pass = this.form.getPassword().getValue();
            String firstName = this.form.getFirstName().getValue();
            String lastName = this.form.getLastName().getValue();
            String phone = this.form.getPhoneNumber().getValue();
            String email = this.form.getEmail().getValue();
            if (userRepository.findAll().stream().anyMatch(u -> u.getUsername().equals(user))) {
                this.form.getErrorMessage().setText("Wpisana nazwa użytkownika już istnieje w bazie.");
                return;
            } else if (this.form.getPassword().getValue().length() < 5) {
                this.form.getErrorMessage().setText("Wpisane hasło jest za krótkie");
                return;
            }
            User newUser = new User(0, user, pass, email, phone, firstName,
                lastName, LocalDateTime.now(), LocalDateTime.now());
            userRepository.save(newUser);
            UI.getCurrent().navigate(MainView.class);
        });
    }
}
