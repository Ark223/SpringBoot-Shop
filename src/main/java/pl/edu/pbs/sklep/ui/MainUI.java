package pl.edu.pbs.sklep.ui;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import javax.annotation.PostConstruct;

@Route("/")
public class MainUI extends VerticalLayout {
    private static final long serialVersionUID = 1L;
    private Text test;

    @PostConstruct
    private void init() {
        this.test = new Text("Hello bro!");
        add(this.test);
    }
}
