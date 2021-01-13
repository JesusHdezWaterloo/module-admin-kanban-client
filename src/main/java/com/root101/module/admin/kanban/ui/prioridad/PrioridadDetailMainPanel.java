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
package com.root101.module.admin.kanban.ui.prioridad;

import com.root101.clean.core.app.services.ExceptionHandler;
import com.root101.module.admin.kanban.core.domain.PrioridadDomain;
import com.root101.module.admin.kanban.ui.module.KanbanModuleNavigator;
import com.root101.module.admin.kanban.ui.module.KanbanSwingModule;
import com.root101.swing.material.components.container.MaterialContainersFactory;
import com.root101.swing.material.components.container.panel._MaterialPanel;
import com.root101.swing.material.standards.MaterialShadow;
import com.root101.swing.models.detail.HeaderDetailPanel;
import com.root101.swing.models.input.dialogs.DialogModelInput;
import com.root101.swing.models.utils.UpdateListener;
import com.root101.utils.interfaces.Update;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class PrioridadDetailMainPanel extends _MaterialPanel implements Update {

    private final UpdateListener updList = new UpdateListener(this);

    public PrioridadDetailMainPanel() {
        initComponents();
        addListeners();
        addPropertyListeners();
        setHeader("Prioridades");
        setIcon(KanbanModuleNavigator.ICON_PRIORIDAD);
    }

    private void initComponents() {
        this.setBorder(new EmptyBorder(
                MaterialShadow.OFFSET_TOP + 10,
                MaterialShadow.OFFSET_LEFT + 10,
                MaterialShadow.OFFSET_BOTTOM + 10,
                MaterialShadow.OFFSET_RIGHT + 10));

        this.setLayout(new BorderLayout());

        header = new HeaderDetailPanel();
        panelCuentasSingle = MaterialContainersFactory.buildPanelTransparent();

        panelCuentasSingle.setLayout(new FlowLayout(FlowLayout.LEFT));

        this.add(header, BorderLayout.NORTH);

        this.add(panelCuentasSingle);
    }

    private HeaderDetailPanel header;
    private JPanel panelCuentasSingle;

    public void setIcon(ImageIcon icon) {
        header.setIcon(icon);
    }

    protected String getSearchText() {
        return header.getSearchText();
    }

    public void setHeader(String text) {
        header.setHeaderText(text);
    }

    public void rellenarCuentas(List<PrioridadDomain> prioridades) {
        panelCuentasSingle.removeAll();
        for (PrioridadDomain cuenta : prioridades) {
            panelCuentasSingle.add(buildSingle(cuenta));
        }

        //los 2 pa que pinte bien
        this.revalidate();
        this.repaint();
    }

    protected PrioridadSingleDetail buildSingle(PrioridadDomain prioridad) {
        return PrioridadSingleDetail.from(prioridad);
    }

    private void addListeners() {
        header.addButtonNuevoActionListener((ActionEvent e) -> {
            createAction();
        });
        header.setSearchActionListener((ActionEvent e) -> {
            update();
        });
    }

    public void createAction() {
        new DialogModelInput(this, PrioridadInputView.from());
    }

    @Override
    public void update() {
        rellenarCuentas(KanbanSwingModule.prioridadUC.findAll(getSearchText()));
    }

    private void addPropertyListeners() {
        KanbanSwingModule.prioridadUC.addPropertyChangeListener(updList);
    }
}
