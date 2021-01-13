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

import com.root101.clean.core.app.services.ExceptionHandler;
import com.root101.clean.core.app.services.NavigationService;
import com.root101.clean.swing.utils.DashBoardComponent;
import com.root101.module.admin.kanban.core.domain.ProyectoDomain;
import com.root101.module.admin.kanban.ui.columna.ColumnaDetailView;
import com.root101.module.admin.kanban.ui.kanban.KanbanProyecto;
import com.root101.module.admin.kanban.ui.prioridad.PrioridadDetailMainPanel;
import com.root101.module.admin.kanban.ui.proyecto.ProyectoDetailView;
import com.root101.module.admin.kanban.ui.tarea.TareaDetailViewHistorico;
import com.root101.module.admin.kanban.ui.utils.KanbanIcons;
import com.root101.swing.material.standards.MaterialIcons;
import com.root101.swing.derivable_icons.DerivableIcon;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class KanbanModuleNavigator implements NavigationService {

    public static final DerivableIcon ICON_KANBAN_PROJECT = KanbanIcons.KANBAN;//MaterialIcons.GRID_ON;

    public static final String GROUP = "modulos.kanaban";

    public static final String KANBAN = "Kanban";
    public static final String PROYECTO = "Proyectos";
    public static final String COLUMNA = "Columnas";
    public static final String PRIORIDAD = "Prioridades";
    public static final String TAREA = "Tareas";

    public static final DerivableIcon ICON_KANBAN = MaterialIcons.DASHBOARD;
    public static final DerivableIcon ICON_PROYECTO = MaterialIcons.NEXT_WEEK;
    public static final DerivableIcon ICON_COLUMNA = MaterialIcons.VIEW_COLUMN;
    public static final DerivableIcon ICON_PRIORIDAD = MaterialIcons.WARNING;
    public static final DerivableIcon ICON_TAREA = MaterialIcons.NEW_RELEASES;

    public static final DerivableIcon ICON_FOLDER = MaterialIcons.OPEN_IN_NEW;
    public static final DerivableIcon ICON_BROWSER = MaterialIcons.OPEN_IN_BROWSER;
    public static final DerivableIcon ICON_COPY = MaterialIcons.CONTENT_COPY;

    public static final String NAV_PROYECTO = GROUP + ".proyecto";
    public static final String NAV_COLUMNA = GROUP + ".columna";
    public static final String NAV_PRIORIDAD = GROUP + ".prioridad";
    public static final String NAV_TAREA = GROUP + ".tareas";

    @Override
    public void navigateTo(String string, Object... os) {
        switch (string) {
        }
    }

    public List<DashBoardComponent> createComponents() {
        List<DashBoardComponent> list = new ArrayList<>();
        for (ProyectoDomain proyectoDomain : KanbanSwingModule.proyectoUC.findAll()) {
            if (proyectoDomain.isKanban()) {
                list.add(DashBoardComponent.from(
                        proyectoDomain.nameFixed(),
                        proyectoDomain.getNombreProyecto(),
                        ICON_KANBAN_PROJECT,
                        KanbanModuleNavigator.GROUP + "." + proyectoDomain.getNombreProyecto(),
                        KanbanProyecto.from(proyectoDomain)));
            }
        }

        list.add(DashBoardComponent.from(
                KanbanModuleNavigator.PROYECTO,
                KanbanModuleNavigator.ICON_PROYECTO,
                KanbanModuleNavigator.NAV_PROYECTO,
                new ProyectoDetailView()));

        list.add(DashBoardComponent.from(
                KanbanModuleNavigator.COLUMNA,
                KanbanModuleNavigator.ICON_COLUMNA,
                KanbanModuleNavigator.NAV_COLUMNA,
                new ColumnaDetailView()));

        list.add(DashBoardComponent.from(
                KanbanModuleNavigator.TAREA,
                KanbanModuleNavigator.ICON_TAREA,
                KanbanModuleNavigator.NAV_TAREA,
                new TareaDetailViewHistorico()));

        list.add(DashBoardComponent.from(
                KanbanModuleNavigator.PRIORIDAD,
                KanbanModuleNavigator.ICON_PRIORIDAD,
                KanbanModuleNavigator.NAV_PRIORIDAD,
                new PrioridadDetailMainPanel()));
        return list;
    }
}
