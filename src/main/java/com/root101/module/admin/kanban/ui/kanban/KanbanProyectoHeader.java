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
package com.root101.module.admin.kanban.ui.kanban;

import com.root101.module.admin.kanban.core.domain.ProyectoDomain;
import com.root101.module.admin.kanban.ui.module.KanbanModuleNavigator;
import com.root101.module.admin.kanban.ui.module.KanbanSwingModule;
import com.root101.module.admin.kanban.ui.proyecto.ProyectoInputView;
import com.jhw.module.util.personalization.core.domain.Personalization;
import com.jhw.module.util.personalization.services.PersonalizationHandler;
import com.jhw.swing.material.components.button.MaterialButtonIcon;
import com.jhw.swing.material.components.button.MaterialButtonsFactory;
import com.jhw.swing.material.components.container.layout.HorizontalLayoutContainer;
import com.jhw.swing.material.components.container.panel._MaterialPanelComponent;
import com.jhw.swing.material.components.labels.MaterialLabel;
import com.jhw.swing.material.components.labels.MaterialLabelsFactory;
import com.jhw.swing.material.standards.MaterialFontRoboto;
import com.jhw.swing.models.input.dialogs.DialogModelInput;
import com.jhw.swing.util.interfaces.BindableComponent;
import com.root101.utils.interfaces.Update;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class KanbanProyectoHeader extends _MaterialPanelComponent implements Update, BindableComponent<ProyectoDomain> {

    public static KanbanProyectoHeader from() {
        return new KanbanProyectoHeader();
    }

    private ProyectoDomain proyecto;

    public KanbanProyectoHeader() {
        initComponents();
        addListeners();
    }

    private void initComponents() {
        setGap(0);
        setLayout(new BorderLayout());

        labelNombreProyecto = MaterialLabelsFactory.build();
        labelNombreProyecto.setHorizontalAlignment(SwingConstants.CENTER);
        labelNombreProyecto.setFont(MaterialFontRoboto.BOLD.deriveFont(24f));

        this.add(labelNombreProyecto, BorderLayout.CENTER);

        buttonCarpeta = MaterialButtonsFactory.buildIconTransparent(KanbanModuleNavigator.ICON_FOLDER);
        buttonCarpeta.setToolTipText("Ir a la carpeta");

        buttonBrowser = MaterialButtonsFactory.buildIconTransparent(KanbanModuleNavigator.ICON_BROWSER);
        buttonBrowser.setToolTipText("Ir al repo online");

        buttonCopy = MaterialButtonsFactory.buildIconTransparent(KanbanModuleNavigator.ICON_COPY);
        buttonCopy.setToolTipText("Copiar url local");

        buttonEdit = MaterialButtonsFactory.buildIconTransparent(PersonalizationHandler.getDerivableIcon(Personalization.KEY_ICON_BUTTON_EDIT));
        buttonEdit.setToolTipText("Editar");

        HorizontalLayoutContainer.builder panelActions = HorizontalLayoutContainer.builder();
        panelActions.add(buttonCarpeta);
        panelActions.add(buttonBrowser);
        panelActions.add(buttonCopy);
        panelActions.add(buttonEdit);

        JPanel panel = panelActions.build();
        panel.setBorder(new EmptyBorder(0, 0, 0, 5));
        this.add(panel, BorderLayout.EAST);
    }

    private MaterialLabel labelNombreProyecto;
    private MaterialButtonIcon buttonCarpeta;
    private MaterialButtonIcon buttonBrowser;
    private MaterialButtonIcon buttonCopy;
    private MaterialButtonIcon buttonEdit;

    private void addListeners() {
        buttonCarpeta.addActionListener((ActionEvent e) -> {
            KanbanSwingModule.proyectoUC.irACarpeta(proyecto);
        });
        buttonBrowser.addActionListener((ActionEvent e) -> {
            KanbanSwingModule.proyectoUC.irARepoOnline(proyecto);
        });
        buttonCopy.addActionListener((ActionEvent e) -> {
            KanbanSwingModule.proyectoUC.copiarURLLocal(proyecto);
        });
        buttonEdit.addActionListener((ActionEvent e) -> {
            DialogModelInput.from(ProyectoInputView.fromModel(proyecto));
        });
    }

    @Override
    public ProyectoDomain getObject() {
        return proyecto;
    }

    @Override
    public void update() {
        labelNombreProyecto.setObject(proyecto.getNombreProyecto());
    }

    @Override
    public void setObject(ProyectoDomain t) {
        this.proyecto = t;
        update();
    }

}
