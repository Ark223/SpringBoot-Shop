package pl.edu.pbs.sklep.ui;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("/register")
public class RegisterView extends VerticalLayout {
    private static final long serialVersionUID = 1L;

    public RegisterView() {
        setSizeFull();
        getStyle().set("background-color", "#ffffff");
        RegisterForm form = new RegisterForm();
        setHorizontalComponentAlignment(Alignment.CENTER, form);
        add(form);
        RegisterBind bind = new RegisterBind(form);
        bind.validateAccount();
    }
}
