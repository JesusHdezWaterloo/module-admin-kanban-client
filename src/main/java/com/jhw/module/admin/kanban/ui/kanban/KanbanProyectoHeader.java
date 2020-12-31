/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.admin.kanban.ui.kanban;

import com.jhw.module.admin.kanban.core.domain.ProyectoDomain;
import com.jhw.module.admin.kanban.ui.module.KanbanModuleNavigator;
import com.jhw.module.admin.kanban.ui.module.KanbanSwingModule;
import com.jhw.module.admin.kanban.ui.proyecto.ProyectoInputView;
import com.jhw.module.util.personalization.core.domain.Personalization;
import com.jhw.module.util.personalization.services.PersonalizationHandler;
import com.jhw.swing.material.components.button.MaterialButtonIcon;
import com.jhw.swing.material.components.button.MaterialButtonsFactory;
import com.jhw.swing.material.components.container.MaterialContainersFactory;
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
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
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
