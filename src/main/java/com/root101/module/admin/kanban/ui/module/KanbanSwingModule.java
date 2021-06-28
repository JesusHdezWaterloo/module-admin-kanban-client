/*
 * Copyright 2021 Root101 (jhernandezb96@gmail.com, +53-5-426-8660).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Or read it directly from LICENCE.txt file at the root of this project.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.root101.module.admin.kanban.ui.module;

import com.root101.clean.core.domain.services.ResourceHandler;
import com.root101.clean.swing.app.AbstractSwingApplication;
import com.root101.clean.swing.app.DefaultAbstractSwingMainModule;
import com.root101.clean.swing.app.dashboard.DashBoardSimple;
import com.root101.clean.swing.app.dashboard.DashboardConstants;
import com.root101.clean.swing.utils.DashBoardComponent;
import com.root101.module.admin.kanban.consume.module.KanbanConsumeCoreModule;
import com.root101.swing.material.components.taskpane.CollapseMenu;
import com.root101.swing.models.utils.UpdateListener;
import com.root101.module.admin.kanban.consume.usecase_def.*;
import com.root101.module.admin.kanban.service.ResourceKeysClient;
import com.root101.module.admin.kanban.service.ResourceKeysStandard;
import com.root101.utils.interfaces.Update;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
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
        columnaUC = KanbanConsumeCoreModule.getInstance().getImplementation(ColumnaUseCaseConsume.class);
        prioridadUC = KanbanConsumeCoreModule.getInstance().getImplementation(PrioridadUseCaseConsume.class);
        proyectoUC = KanbanConsumeCoreModule.getInstance().getImplementation(ProyectoUseCaseConsume.class);
        tareaUC = KanbanConsumeCoreModule.getInstance().getImplementation(TareaUseCaseConsume.class);
    }

    private KanbanSwingModule() {
        updList = new UpdateListener(this);
        KanbanSwingModule.proyectoUC.addPropertyChangeListener(updList);
    }

    public static KanbanSwingModule init() {
        System.out.println("Iniciando 'Kanban'");

        ResourceHandler.registerInternal(ResourceKeysClient.RESOURCE_URL);
        ResourceHandler.registerInternal(ResourceKeysStandard.RESOURCE_URL);
        
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
