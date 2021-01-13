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

import com.root101.clean.core.app.services.ExceptionHandler;
import com.root101.module.admin.kanban.core.domain.ColumnaDomain;
import com.root101.module.admin.kanban.ui.module.KanbanModuleNavigator;
import com.root101.module.admin.kanban.ui.module.KanbanSwingModule;
import com.root101.swing.models.detail._MaterialPanelDetail;
import com.root101.swing.material.components.table.Column;
import com.root101.swing.models.input.dialogs.DialogModelInput;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class ColumnaDetailView extends _MaterialPanelDetail<ColumnaDomain> {

    private static final String COL_NOMBRE = "Nombre";
    private static final String COL_POS = "Posición";
    private static final String COL_DESC = "Descripción";

    public ColumnaDetailView() {
        super(
                Column.builder().name(COL_NOMBRE).build(),
                Column.builder().name(COL_POS).build(),
                Column.builder().name(COL_DESC).build()
        );

        this.personalize();
    }

    private void personalize() {
        //addActionsExtra();

        this.setHeaderText("Columnas");
        this.setIcon(KanbanModuleNavigator.ICON_COLUMNA);

        this.setActionColumnButtonsVisivility(true, true, false);//no pone el view, no esta implementado todavia
    }

    @Override
    public void update() {
        setCollection(KanbanSwingModule.columnaUC.findAll());
    }

    @Override
    public Object[] getRowObject(ColumnaDomain obj) {
        return new Object[]{
            obj.getNombreColumna(),
            obj.getPosicion(),
            obj.getDescripcion()};
    }

    @Override
    protected void buttonNuevoActionListener() {
        new DialogModelInput(this, ColumnaInputView.from());
    }

    @Override
    protected ColumnaDomain deleteAction(ColumnaDomain obj) {
        return KanbanSwingModule.columnaUC.destroy(obj);
    }

    @Override
    protected void editAction(ColumnaDomain obj) {
        new DialogModelInput(this, ColumnaInputView.fromModel(obj));
    }

    @Override
    protected void viewAction(ColumnaDomain obj) {
        System.out.println("NO NECESARIO TODAVÍA.");
    }
    /*
    private void addActionsExtra() {
        this.addActionExtra(new AbstractAction("Contratar", MaterialIcons.ASSIGNMENT_IND.deriveIcon(18f)) {
            @Override
            public void actionPerformed(ActionEvent e) {
                onContratarEmpleadoActionPerformed();
            }
        });
    }

    private void onContratarEmpleadoActionPerformed() {
        new DialogModelInput(this, ContratoEmpleadoInputView.from(getSelectedElement()));
    }*/
}
