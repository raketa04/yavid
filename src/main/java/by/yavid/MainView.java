package by.yavid;

import by.yavid.Entity.Workbase.Bazis.KDList;
import by.yavid.Service.Bazis.KDListService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

//@Route
public class MainView extends VerticalLayout {

    private KDListService kdListService;

    final Grid<KDList> grid;

    final TextField filter;

    private final Button addNewBtn;

    public MainView(KDListService kdListService) {
        this.kdListService = kdListService;
        this.grid = new Grid<>(KDList.class);
        this.filter = new TextField();
        this.addNewBtn = new Button("New customer", VaadinIcon.PLUS.create());

        // build layout
        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        add(actions, grid);

        grid.setHeight("600px");
        grid.setColumns("numberKD", "remark", "typeProduct.nameGroup","user.userName");
        grid.getColumnByKey("numberKD").setWidth("120px").setFlexGrow(0);

        filter.setPlaceholder("Filter by last name");

        // Hook logic to components

        // Replace listing with filtered content when user changes filter
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listCustomers(e.getValue()));

        // Connect selected Customer to editor or hide if none is selected
        // Instantiate and edit new Customer the new button is clicked

        // Listen changes made by the editor, refresh data from backend

        // Initialize listing
        listCustomers(null);
    }

    // tag::listCustomers[]
    void listCustomers(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            List<KDList> list = kdListService.getAllKdList();
            grid.setItems(list);
        }
        else {
            grid.setItems(kdListService.getKdList(Integer.parseInt(filterText)));
        }
    }
    // end::listCustomers[]

}