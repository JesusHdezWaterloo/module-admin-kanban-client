package com.jhw.module.admin.kanban.ui.module;

import com.root101.clean.core.app.services.ExceptionHandler;
import com.root101.clean.core.app.services.NavigationService;
import com.root101.clean.swing.utils.DashBoardComponent;
import com.jhw.module.admin.kanban.core.domain.ProyectoDomain;
import com.jhw.module.admin.kanban.ui.columna.ColumnaDetailView;
import com.jhw.module.admin.kanban.ui.kanban.KanbanProyecto;
import com.jhw.module.admin.kanban.ui.prioridad.PrioridadDetailMainPanel;
import com.jhw.module.admin.kanban.ui.proyecto.ProyectoDetailView;
import com.jhw.module.admin.kanban.ui.tarea.TareaDetailViewHistorico;
import com.jhw.module.admin.kanban.ui.utils.KanbanIcons;
import com.jhw.swing.material.standards.MaterialIcons;
import com.jhw.swing.derivable_icons.DerivableIcon;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class KanbanModuleNavigator implements NavigationService {

    //test mientras tanto
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
        try {
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

        } catch (Exception e) {
            ExceptionHandler.handleException(e);
        }
        return list;
    }
}
