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
package com.root101.module.admin.kanban.ui.columna;

import com.root101.module.admin.kanban.core.domain.ColumnaDomain;
import com.root101.module.admin.kanban.ui.export.ColumnaExport;
import com.root101.module.admin.kanban.ui.module.KanbanModuleNavigator;
import com.root101.module.admin.kanban.ui.module.KanbanSwingModule;
import com.root101.swing.material.components.table.Column;
import com.root101.swing.models.clean.CleanDetailCRUDDragDrop;
import com.root101.swing.models.input.panels.ModelPanel;
import java.util.List;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class ColumnaDetailView extends CleanDetailCRUDDragDrop<ColumnaDomain> {

    private static final String COL_NOMBRE = "Nombre";
    private static final String COL_POS = "Posición";
    private static final String COL_DESC = "Descripción";

    public ColumnaDetailView() {
        super(
                Column.builder().name(COL_NOMBRE).build(),
                Column.builder().name(COL_POS).build(),
                Column.builder().name(COL_DESC).build()
        );
    }

    @Override
    protected void personalize() {
        this.setHeaderText("Columnas");
        this.setIcon(KanbanModuleNavigator.ICON_COLUMNA);

        this.setActionColumnButtonsVisivility(true, true, false);//no pone el view, no esta implementado todavia

        this.setExportConfig(ColumnaExport.from(this));
    }

    @Override
    protected List<ColumnaDomain> getListUpdate() {
        return KanbanSwingModule.columnaUC.findAll();
    }

    @Override
    public Object[] getRowObject(ColumnaDomain obj) {
        return new Object[]{
            obj.getNombreColumna(),
            obj.getPosicion(),
            obj.getDescripcion()};
    }

    @Override
    protected void addPropertyChange() {
        KanbanSwingModule.columnaUC.addPropertyChangeListener(this);
    }

    @Override
    protected ModelPanel<ColumnaDomain> getModelPanelNew() {
        return ColumnaInputView.from();
    }

    @Override
    protected ModelPanel<ColumnaDomain> getModelPanelEdit(ColumnaDomain obj) {
        return ColumnaInputView.fromModel(obj);
    }

    @Override
    protected ColumnaDomain deleteAction(ColumnaDomain obj) {
        return KanbanSwingModule.columnaUC.destroy(obj);
    }

}
