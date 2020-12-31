/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.admin.kanban.ui.prioridad;

import com.root101.clean.core.app.services.ExceptionHandler;
import com.jhw.module.admin.kanban.core.domain.PrioridadDomain;
import com.jhw.module.admin.kanban.ui.module.KanbanModuleNavigator;
import com.jhw.module.admin.kanban.ui.module.KanbanSwingModule;
import com.jhw.swing.material.components.container.MaterialContainersFactory;
import com.jhw.swing.material.components.container.panel._MaterialPanel;
import com.jhw.swing.material.standards.MaterialShadow;
import com.jhw.swing.models.detail.HeaderDetailPanel;
import com.jhw.swing.models.input.dialogs.DialogModelInput;
import com.jhw.swing.models.utils.UpdateListener;
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
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
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
        try {
            rellenarCuentas(KanbanSwingModule.prioridadUC.findAll(getSearchText()));
        } catch (Exception e) {
            ExceptionHandler.handleException(e);
        }
    }

    private void addPropertyListeners() {
        KanbanSwingModule.prioridadUC.addPropertyChangeListener(updList);
    }
}
