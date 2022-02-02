package pl.edu.pbs.sklep.ui;

import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import java.util.stream.Stream;

public class RegisterForm extends FormLayout {
    private H3 title;
    private TextField firstName;
    private TextField lastName;
    private TextField username;
    private TextField phoneNumber;
    private TextField address;
    private EmailField email;
    private PasswordField password;
    private PasswordField passwordConfirm;
    private Checkbox allow;
    private Span errorMessageField;
    private Button submitButton;

    public RegisterForm() {
        this.title = new H3("Rejestracja konta");
        this.firstName = new TextField("Imię");
        this.lastName = new TextField("Nazwisko");
        this.username = new TextField("Nazwa użytkownika");
        this.phoneNumber = new TextField("Numer telefonu");
        this.address = new TextField("Adres zamieszkania");
        this.email = new EmailField("Adres email");
        this.allow = new Checkbox("Czy zgadzasz się na przetwarzanie danych?");
        this.allow.getStyle().set("margin-top", "10px");
        this.password = new PasswordField("Hasło");
        this.passwordConfirm = new PasswordField("Potwierdź hasło");
        this.errorMessageField = new Span();
        this.submitButton = new Button("Zarejestruj konto");
        this.submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        setRequiredIndicatorVisible(this.username, this.address,
            this.email, this.password, this.passwordConfirm);
        add(this.title, this.firstName, this.lastName,
            this.username, this.phoneNumber, this.address,
            this.email, this.password, this.passwordConfirm,
            this.allow, this.errorMessageField, this.submitButton);
        setMaxWidth("500px");
        setResponsiveSteps(
            new ResponsiveStep("0", 1, ResponsiveStep.LabelsPosition.TOP),
            new ResponsiveStep("490px", 2, ResponsiveStep.LabelsPosition.TOP));

        setColspan(this.title, 2);
        setColspan(this.address, 2);
        setColspan(this.email, 2);
        setColspan(this.errorMessageField, 2);
        setColspan(this.submitButton, 2);
    }

    public TextField getFirstName() { return this.firstName; }
    public TextField getLastName() { return this.lastName; }
    public TextField getUsername() { return this.username; }
    public TextField getPhoneNumber() { return this.phoneNumber; }
    public TextField getAddress() { return this.address; }
    public EmailField getEmail() { return this.email; }
    public PasswordField getPassword() { return this.password; }
    public Button getSubmitButton() { return this.submitButton; }
    public Span getErrorMessage() { return this.errorMessageField; }

    private void setRequiredIndicatorVisible(HasValueAndElement<?, ?>... components) {
        Stream.of(components).forEach(comp -> comp.setRequiredIndicatorVisible(true));
    }
}
