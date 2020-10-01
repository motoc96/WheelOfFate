package wof.views.main;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;


import wof.model.DateAndShift;
import wof.utility.ScheduleUtility;




/**
 * The main view is a top-level placeholder for other views.
 */
@JsModule("./styles/shared-styles.js")
@CssImport(value = "./styles/views/main/main-view.css", themeFor = "vaadin-app-layout")
@Theme(value = Lumo.class, variant = Lumo.DARK)
@CssImport("./styles/views/main/main-view.css")

@Route("")
public class MainView extends VerticalLayout {

    public MainView(ScheduleUtility scheduleUtility) {
        scheduleUtility.getEngineers();
        Grid<DateAndShift> grid =new Grid<>(DateAndShift.class);
        grid.setItems(scheduleUtility.getDateAndShiftPool());
        grid.removeColumnByKey("occupied");
        scheduleUtility.getEngineers();
        add(grid);
    }


}