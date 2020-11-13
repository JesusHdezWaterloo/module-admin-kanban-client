/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.admin.kanban.ui.kanban;

import com.clean.core.app.services.ExceptionHandler;
import com.jhw.module.admin.kanban.core.domain.ColumnaDomain;
import com.jhw.module.admin.kanban.core.domain.ColumnaProyectVolatile;
import com.jhw.module.admin.kanban.core.domain.ProyectoDomain;
import com.jhw.module.admin.kanban.core.domain.TareaDomain;
import com.jhw.module.admin.kanban.ui.module.KanbanSwingModule;
import com.jhw.swing.material.components.container.MaterialContainersFactory;
import com.jhw.swing.material.components.container.panel._MaterialPanelComponent;
import com.jhw.swing.material.components.container.panel._PanelGradient;
import com.jhw.swing.material.components.labels.MaterialLabel;
import com.jhw.swing.material.components.labels.MaterialLabelsFactory;
import com.jhw.swing.material.standards.MaterialFontRoboto;
import com.jhw.swing.models.utils.UpdateListener;
import com.jhw.utils.interfaces.Update;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class KanbanProyecto extends _PanelGradient implements Update {

    public static KanbanProyecto from(ProyectoDomain proyecto) {
        return new KanbanProyecto(proyecto);
    }

    private final ProyectoDomain proyecto;

    private final UpdateListener updList = new UpdateListener(this) {
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            //si es una tarea pero no es de este proyecto, no actualizo
            if (evt.getNewValue() instanceof TareaDomain) {
                TareaDomain t = (TareaDomain) evt.getNewValue();
                if (!t.getProyectoFk().equals(proyecto)) {
                    return;
                }
            }
            super.propertyChange(evt);
        }
    };

    public KanbanProyecto(ProyectoDomain proyecto) {
        this.proyecto = proyecto;
        initComponents();
        addPropertyListeners();
        update();
    }

    private void initComponents() {
        this.setLayout(new BorderLayout());

        panelColumnas = MaterialContainersFactory.buildPanelTransparent();
        header = KanbanProyectoHeader.from();

        this.add(header, BorderLayout.NORTH);
        this.add(panelColumnas, BorderLayout.CENTER);
    }

    private JPanel panelColumnas;
    private KanbanProyectoHeader header;

    @Override
    public void update() {
        updateColumns();
        header.setObject(proyecto);
    }

    private void updateColumns() {
        try {
            panelColumnas.removeAll();//quito todo

            //busco las columnas
            List<ColumnaDomain> columnas = KanbanSwingModule.columnaUC.findAll();

            //pongo el layout en dependencia de las columnas
            panelColumnas.setLayout(new GridLayout(1, columnas.size()));

            //relleno las columnas
            for (ColumnaDomain c : columnas) {
                panelColumnas.add(KanbanColumn.from(new ColumnaProyectVolatile(proyecto, c)));
            }
        } catch (Exception e) {
            ExceptionHandler.handleException(e);
        }
    }

    private void addPropertyListeners() {
        KanbanSwingModule.tareaUC.addPropertyChangeListener(updList);
        KanbanSwingModule.columnaUC.addPropertyChangeListener(updList);
    }
}
