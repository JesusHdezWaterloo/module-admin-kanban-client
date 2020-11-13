package com.jhw.module.admin.kanban.ui.module;

import com.clean.swing.app.AbstractSwingApplication;
import com.clean.swing.app.DefaultAbstractSwingMainModule;
import com.clean.swing.app.dashboard.DashBoardSimple;
import com.clean.swing.app.dashboard.DashboardConstants;
import com.clean.swing.utils.DashBoardComponent;
import com.jhw.module.admin.kanban.consume.module.KanbanConsumeCoreModule;
import com.jhw.module.admin.kanban.consume.usecase_def.*;
import com.jhw.module.admin.kanban.service.ResourceServiceImplementation;
import com.jhw.swing.material.components.taskpane.CollapseMenu;
import com.jhw.swing.models.utils.UpdateListener;
import com.jhw.utils.interfaces.Update;

public class KanbanSwingModule extends DefaultAbstractSwingMainModule implements Update {

    private final UpdateListener updList;
    private final CollapseMenu menu = new CollapseMenu(KanbanModuleNavigator.ICON_KANBAN, KanbanModuleNavigator.KANBAN);
    private DashBoardSimple dash;
    private AbstractSwingApplication app;

    private final KanbanModuleNavigator navigator = new KanbanModuleNavigator();

    public final static ColumnaUseCaseConsume columnaUC;
    public final static PrioridadUseCaseConsume prioridadUC;
    public final static ProyectoUseCaseConsume proyectoUC;
    public final static TareaUseCaseConsume tareaUC;

    static {
        KanbanConsumeCoreModule.init();

        columnaUC = KanbanConsumeCoreModule.getInstance().getImplementation(ColumnaUseCaseConsume.class);
        prioridadUC = KanbanConsumeCoreModule.getInstance().getImplementation(PrioridadUseCaseConsume.class);
        proyectoUC = KanbanConsumeCoreModule.getInstance().getImplementation(ProyectoUseCaseConsume.class);
        tareaUC = KanbanConsumeCoreModule.getInstance().getImplementation(TareaUseCaseConsume.class);

        ResourceServiceImplementation.init();
    }

    private KanbanSwingModule() {
        updList = new UpdateListener(this);
        KanbanSwingModule.proyectoUC.addPropertyChangeListener(updList);
    }

    public static KanbanSwingModule init() {
        System.out.println("Iniciando 'Kanban'");

        return new KanbanSwingModule();
    }

    @Override
    public void register(AbstractSwingApplication app) {
        registerMainElements(app);
    }

    private void registerMainElements(AbstractSwingApplication app) {
        this.app = app;
        this.dash = app.rootView().dashboard();
        this.dash.addKeyValue(DashboardConstants.MAIN_ELEMENT, menu);
        update();
    }

    @Override
    public void navigateTo(String string, Object... o) {
        navigator.navigateTo(string, o);
    }

    @Override
    public void update() {
        menu.clear();//limpia el menu
        
        String lastView = dash.getSelectedViewName();
        dash.removeGroupView(KanbanModuleNavigator.GROUP);//limpia las vistas

        //agrega todo lo demas
        for (DashBoardComponent c : navigator.createComponents()) {
            dash.addView(c.nav, c.view);
            menu.addMenuItem(c.buildAction(app));
        }

        //repinta el dashboard
        dash.format();
        
        dash.showView(lastView);
    }
}
