package com.jhw.module.admin.kanban.ui.columna;

import com.jhw.module.admin.kanban.core.domain.ColumnaDomain;
import com.jhw.module.admin.kanban.ui.module.KanbanModuleNavigator;
import com.jhw.module.admin.kanban.ui.module.KanbanSwingModule;
import com.jhw.swing.models.input.icbs.InputComboBoxSelection;
import com.jhw.swing.models.input.panels.ModelPanel;
import java.util.List;

/**
 *
 * @author Jesús Hernández Barrios (jhernandezb96@gmail.com)
 */
public class ColumnaICBS extends InputComboBoxSelection<ColumnaDomain> {

    public ColumnaICBS() {
        setLabel("Columna");
        setIcon(KanbanModuleNavigator.ICON_PROYECTO);
    }

    @Override
    public List<ColumnaDomain> getList() throws Exception {
        return KanbanSwingModule.columnaUC.findAll();
    }

    @Override
    public ModelPanel<ColumnaDomain> inputPanel() {
        return ColumnaInputView.from();
    }

    @Override
    protected void addPropertyChange() {
        KanbanSwingModule.columnaUC.addPropertyChangeListener(this);
    }
}
