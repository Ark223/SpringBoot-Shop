package pl.edu.pbs.sklep.ui;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pbs.sklep.repository.UserRepository;
import javax.annotation.PostConstruct;

@Route("/register")
public class RegisterView extends VerticalLayout {
    private static final long serialVersionUID = 1L;

    @Autowired
    UserRepository userRepository;

    @PostConstruct
    public void init() {
        setSizeFull();
        getStyle().set("background-color", "#ffffff");
        RegisterForm form = new RegisterForm();
        setHorizontalComponentAlignment(Alignment.CENTER, form);
        add(form);
        RegisterBind bind = new RegisterBind(form, userRepository);
        bind.validateAccount();
    }
}
