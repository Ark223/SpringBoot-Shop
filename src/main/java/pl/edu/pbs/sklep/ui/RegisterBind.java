package pl.edu.pbs.sklep.ui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import pl.edu.pbs.sklep.model.User;
import pl.edu.pbs.sklep.repository.UserRepository;
import java.time.LocalDateTime;

public class RegisterBind {
    private UserRepository userRepository;
    private RegisterForm form;

    public RegisterBind(RegisterForm form, UserRepository userRepository) {
        this.userRepository = userRepository;
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
            if (user.equals("") || pass.equals("") || email.equals("")) {
                Notification.show("Proszę wypełnić wymagane pola: nazwa użytkownika, hasło i email.");
                return;
            } else if (this.userRepository.findAll().stream().anyMatch(u -> u.getUsername().equals(user))) {
                Notification.show("Wpisana nazwa użytkownika już istnieje w bazie.");
                return;
            } else if (this.form.getPassword().getValue().length() < 5) {
                Notification.show("Wpisane hasło jest za krótkie");
                return;
            }
            User newUser = new User(0, user, pass, email, phone, firstName,
                lastName, LocalDateTime.now(), LocalDateTime.now());
            this.userRepository.save(newUser);
            UI.getCurrent().navigate(MainView.class);
        });
    }
}
