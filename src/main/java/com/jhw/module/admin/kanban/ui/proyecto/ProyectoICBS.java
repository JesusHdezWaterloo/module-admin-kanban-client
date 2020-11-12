package com.jhw.module.admin.kanban.ui.proyecto;

import com.jhw.module.admin.kanban.core.domain.ProyectoDomain;
import com.jhw.module.admin.kanban.ui.module.KanbanModuleNavigator;
import com.jhw.module.admin.kanban.ui.module.KanbanSwingModule;
import com.jhw.swing.models.input.icbs.InputComboBoxSelection;
import com.jhw.swing.models.input.panels.ModelPanel;
import java.util.List;

/**
 *
 * @author Jesús Hernández Barrios (jhernandezb96@gmail.com)
 */
public class ProyectoICBS extends InputComboBoxSelection<ProyectoDomain> {

    public ProyectoICBS() {
        setLabel("Proyecto");
        setIcon(KanbanModuleNavigator.ICON_PROYECTO);
    }

    @Override
    public List<ProyectoDomain> getList() throws Exception {
        return KanbanSwingModule.proyectoUC.findAll();
    }

    @Override
    public ModelPanel<ProyectoDomain> inputPanel() {
        return ProyectoInputView.from();
    }

    @Override
    protected void addPropertyChange() {
        KanbanSwingModule.proyectoUC.addPropertyChangeListener(this);
    }
}
