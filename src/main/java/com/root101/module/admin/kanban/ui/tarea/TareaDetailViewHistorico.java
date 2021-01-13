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
package com.root101.module.admin.kanban.ui.tarea;

import com.root101.clean.core.app.services.ExceptionHandler;
import com.root101.module.admin.kanban.core.domain.TareaDomain;
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
public class TareaDetailViewHistorico extends CleanDetailCRUDDragDrop<TareaDomain> {

    private static final String COL_CODIGO = "Cód.";
    private static final String COL_NOMBRE = "Nombre";
    private static final String COL_PRIORIDAD = "Prior.";
    private static final String COL_PUNTOS = "Ptos.";
    private static final String COL_PROYECTO = "Proyecto";
    private static final String COL_COLUMNA = "Columna";

    public TareaDetailViewHistorico() {
        super(
                Column.builder().name(COL_CODIGO).build(),
                Column.builder().name(COL_NOMBRE).build(),
                Column.builder().name(COL_PRIORIDAD).build(),
                Column.builder().name(COL_PUNTOS).build(),
                Column.builder().name(COL_PROYECTO).build(),
                Column.builder().name(COL_COLUMNA).build()
        );

    }

    @Override
    protected void personalize() {
        addActionsElements();

        this.setAdjustColumns(true);
        this.setHeaderText("Tareas (Histórico)");
        this.setIcon(KanbanModuleNavigator.ICON_TAREA);

        this.setActionColumnButtonsVisivility(true, true, false);//no pone el view, no esta implementado todavia
    }

    @Override
    protected List<TareaDomain> getListUpdate() throws RuntimeException {
        return KanbanSwingModule.tareaUC.findAll();
    }

    @Override
    public Object[] getRowObject(TareaDomain obj) {
        return new Object[]{
            obj.getCodigoTarea(),
            obj.getNombreTarea(),
            obj.getPrioridadFk(),
            obj.getPuntos(),
            obj.getProyectoFk(),
            obj.getColumnaFk()
        };
    }

    @Override
    protected void addPropertyChange() {
        KanbanSwingModule.tareaUC.addPropertyChangeListener(this);
    }

    @Override
    protected ModelPanel<TareaDomain> getModelPanelNew() {
        return TareaInputView.from();
    }

    @Override
    protected ModelPanel<TareaDomain> getModelPanelEdit(TareaDomain obj) {
        return TareaInputView.fromModel(obj);
    }

    @Override
    protected TareaDomain deleteAction(TareaDomain obj) {
        try {
            return KanbanSwingModule.tareaUC.destroy(obj);
        } catch (Exception ex) {
            ExceptionHandler.handleException(ex);
        }
        return null;
    }

    private void addActionsElements() {

    }

}
