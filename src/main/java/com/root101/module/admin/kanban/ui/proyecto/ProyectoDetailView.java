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
package com.root101.module.admin.kanban.ui.proyecto;

import com.root101.module.admin.kanban.core.domain.ProyectoDomain;
import com.root101.module.admin.kanban.ui.export.ProyectoExport;
import com.root101.module.admin.kanban.ui.module.KanbanModuleNavigator;
import com.root101.module.admin.kanban.ui.module.KanbanSwingModule;
import com.root101.swing.material.components.container.MaterialContainersFactory;
import com.root101.swing.material.components.container.layout.HorizontalLayoutContainer;
import com.root101.swing.material.components.labels.MaterialLabel;
import com.root101.swing.material.components.labels.MaterialLabelsFactory;
import com.root101.swing.material.components.table.Column;
import com.root101.swing.material.components.table.editors_renders.component.ComponentCellRender;
import com.root101.swing.material.standards.MaterialColors;
import com.root101.swing.material.standards.MaterialIcons;
import com.root101.swing.models.clean.CleanDetailCRUDDragDrop;
import com.root101.swing.models.input.panels.ModelPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class ProyectoDetailView extends CleanDetailCRUDDragDrop<ProyectoDomain> {

    private static final String COL_NOMBRE = "Nombre";
    private static final String COL_PRIORIDAD = "Prioridad";
    private static final String COL_KANBAN = "Kanban";
    private static final String COL_URL_LOCAL = "URL Local";
    private static final String COL_GITHUB = "GitHub";

    public ProyectoDetailView() {
        super(
                Column.builder().name(COL_NOMBRE).build(),
                Column.builder().name(COL_PRIORIDAD).build(),
                Column.builder().name(COL_KANBAN).build(),
                Column.builder().name(COL_URL_LOCAL).build(),
                Column.builder().name(COL_GITHUB).build()
        );

    }

    @Override
    protected void personalize() {
        addActionsElements();

        this.setAdjustColumns(true);
        this.setHeaderText("Proyectos activos");
        this.setIcon(KanbanModuleNavigator.ICON_PROYECTO);

        getTable().getColumn(COL_GITHUB).setCellRenderer(new ComponentCellRender(false));

        this.setActionColumnButtonsVisivility(true, true, false);//no pone el view, no esta implementado todavia

        this.setExportConfig(ProyectoExport.from(this));
    }

    @Override
    protected List<ProyectoDomain> getListUpdate() throws RuntimeException {
        return KanbanSwingModule.proyectoUC.findAll();
    }

    @Override
    public Object[] getRowObject(ProyectoDomain obj) {
        return new Object[]{
            obj.getNombreProyecto(),
            obj.getPrioridad(),
            obj.isKanban() ? "SI" : "NO",
            obj.urlLocalFixed(),
            getHasGithub(obj)};
    }

    private JPanel getHasGithub(ProyectoDomain obj) {
        JPanel panel = MaterialContainersFactory.buildPanelGradient();
        panel.setLayout(new BorderLayout());

        boolean remote = KanbanSwingModule.proyectoUC.hasRemote(obj);

        HorizontalLayoutContainer.builder hlc = HorizontalLayoutContainer.builder();

        MaterialLabel label = MaterialLabelsFactory.build();
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(UIManager.getFont("Table.font"));
        label.setIcon(remote ? MaterialIcons.TEC_GITHUB : null);
        hlc.add(label);

        panel.add(hlc.build());
        panel.setBackground(remote ? MaterialColors.GREENA_100 : MaterialColors.REDA_100);
        return panel;
    }

    @Override
    protected void addPropertyChange() {
        KanbanSwingModule.proyectoUC.addPropertyChangeListener(this);
    }

    @Override
    protected ModelPanel<ProyectoDomain> getModelPanelNew() {
        return ProyectoInputView.from();
    }

    @Override
    protected ModelPanel<ProyectoDomain> getModelPanelEdit(ProyectoDomain obj) {
        return ProyectoInputView.fromModel(obj);
    }

    @Override
    protected ProyectoDomain deleteAction(ProyectoDomain obj) {
        return KanbanSwingModule.proyectoUC.destroy(obj);
    }

    private void addActionsElements() {
        this.addActionExtra(new AbstractAction("Ir a la carpeta", KanbanModuleNavigator.ICON_FOLDER) {
            @Override
            public void actionPerformed(ActionEvent e) {
                KanbanSwingModule.proyectoUC.irACarpeta(getSelectedElement());
            }
        });
        this.addActionExtra(new AbstractAction("Ir al repo online", KanbanModuleNavigator.ICON_BROWSER) {
            @Override
            public void actionPerformed(ActionEvent e) {
                KanbanSwingModule.proyectoUC.irARepoOnline(getSelectedElement());
            }
        });
        this.addActionExtra(new AbstractAction("Copiar url local", KanbanModuleNavigator.ICON_COPY) {
            @Override
            public void actionPerformed(ActionEvent e) {
                KanbanSwingModule.proyectoUC.copiarURLLocal(getSelectedElement());
            }
        });
    }

}
