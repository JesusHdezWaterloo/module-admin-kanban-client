/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.admin.kanban.ui.kanban;

import com.clean.core.app.services.ExceptionHandler;
import com.jhw.module.admin.kanban.core.domain.ColumnaProyectVolatile;
import com.jhw.module.admin.kanban.core.domain.MoveTarea;
import com.jhw.module.admin.kanban.core.domain.TareaDomain;
import com.jhw.module.admin.kanban.ui.module.KanbanSwingModule;
import com.jhw.module.admin.kanban.ui.tarea.TareaInputView;
import com.jhw.swing.bundles.dnd.DropHandler;
import com.jhw.swing.material.components.container.MaterialContainersFactory;
import com.jhw.swing.material.components.container.panel.*;
import com.jhw.swing.material.components.labels.*;
import com.jhw.swing.material.components.scrollpane.MaterialScrollFactory;
import com.jhw.swing.material.components.scrollpane.MaterialScrollPane;
import com.jhw.swing.material.components.searchfield.MaterialSearchField;
import com.jhw.swing.material.components.searchfield._MaterialSearchField;
import com.jhw.swing.material.injection.MaterialSwingInjector;
import com.jhw.swing.material.standards.MaterialColors;
import com.jhw.swing.models.input.dialogs.DialogModelInput;
import com.jhw.swing.prepared.button.MaterialButtonAddEdit;
import com.jhw.swing.prepared.button.MaterialPreparedButtonsFactory;
import com.jhw.utils.interfaces.Update;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class KanbanColumn extends _MaterialPanelComponent implements Update {

    private final static int DEFAULT_GAP = 0;

    public static KanbanColumn from(ColumnaProyectVolatile colProy) {
        return new KanbanColumn(colProy);
    }

    private final ColumnaProyectVolatile colProy;
    private List<TareaDomain> tareas = new ArrayList<>();

    private DropTarget dropTarget;
    private DropHandler dropHandler;

    public KanbanColumn(ColumnaProyectVolatile colProy) {
        this.colProy = colProy;

        initComponents();
        addListeners();
        update();
    }

    private void initComponents() {
        this.setGap(DEFAULT_GAP);//gap para respetar el sombreado

        header = KanbanColumnHeader.from(colProy);
        this.add(header, BorderLayout.NORTH);

        panelTareas = MaterialContainersFactory.buildPanelTransparent();
        panelTareas.setLayout(new BorderLayout());

        //----------------con scroll----------------
        MaterialScrollPane scroll = MaterialScrollFactory.buildPane();
        scroll.remove(scroll.getHorizontalScrollBar());
        scroll.setViewportView(panelTareas);
        this.add(scroll);
        //----------------con scroll----------------

        //----------------sin scroll----------------
        //this.add(panelTareas);
        //----------------sin scroll----------------
    }

    private KanbanColumnHeader header;
    private JPanel panelTareas;

    @Override
    public void update() {
        try {
            this.tareas = KanbanSwingModule.tareaUC.findByColumnaProyecto(colProy.lightweigth());
        } catch (Exception e) {
        }
        updateColumn();
    }

    private void updateColumn() {
        try {
            //no se puede usar un layout xq usan mig, y se tufa con el DnD
            panelTareas.removeAll();
            JPanel inner = MaterialContainersFactory.buildPanelTransparent();
            inner.setLayout(new GridLayout(tareas.size(), 1));
            for (TareaDomain tarea : tareas) {
                if (tarea.test(header.getSearchText())) {
                    inner.add(TareaSimplePanel.from(tarea));
                }
            }
            panelTareas.add(inner, BorderLayout.NORTH);
        } catch (Exception e) {
            ExceptionHandler.handleException(e);
        }
    }

    private void addListeners() {
        header.addActionListenerButtonAdd((ActionEvent e) -> {
            DialogModelInput.from(TareaInputView.fromColumna(colProy));
        });
        header.setSearchActionListener((ActionEvent e) -> {
            updateColumn();
        });
    }

    @Override
    public void addNotify() {
        super.addNotify();
        dropHandler = new DropHandler() {
            @Override
            protected void doDrop(JPanel dragged, Component targetDrop) throws Exception {
                if (dragged instanceof TareaSimplePanel) {
                    TareaSimplePanel tarea = (TareaSimplePanel) dragged;
                    TareaDomain t = tarea.getObject();
                    t.setProyectoFk(colProy.getProyecto());
                    t.setColumnaFk(colProy.getColumna());
                    KanbanSwingModule.tareaUC.move(MoveTarea.from(t, colProy.getColumna()));
                }
            }
        };
        dropTarget = new DropTarget(this, DnDConstants.ACTION_MOVE, dropHandler, true);
    }

    @Override
    public void removeNotify() {
        super.removeNotify();
        dropTarget.removeDropTargetListener(dropHandler);
    }

    private static class KanbanColumnHeader extends _PanelTransparent {

        public static KanbanColumnHeader from(ColumnaProyectVolatile colProy) {
            KanbanColumnHeader columnHeader = MaterialSwingInjector.getImplementation(KanbanColumnHeader.class);
            columnHeader.setHeader(colProy.getColumna().getNombreColumna());
            columnHeader.setToolTipText(colProy.getColumna().getDescripcion());
            return columnHeader;
        }

        public KanbanColumnHeader() {
            initComponents();
        }

        private void initComponents() {
            _MaterialPanelComponent back = (_MaterialPanelComponent) MaterialContainersFactory.buildPanelComponent();
            back.setBackground(MaterialColors.GREY_300);
            back.setGap(0);
            back.setLayout(new BorderLayout());
            searchField = _MaterialSearchField.from();

            labelHeader = MaterialLabelsFactory.build();
            labelHeader.setHorizontalAlignment(SwingConstants.CENTER);
            labelHeader.setFont(labelHeader.getFont().deriveFont(Font.BOLD));
            back.add(labelHeader);

            buttonAdd = MaterialPreparedButtonsFactory.buildAddEdit();
            buttonAdd.setToolTipText("Agregar tarea en esta columna");
            buttonAdd.isCreated(true);
            buttonAdd.setText("");
            int w = (int) (2f * buttonAdd.getIcon().getIconWidth());
            buttonAdd.setPreferredSize(new Dimension(w, w - 5));//el menos para emparejar el tamaño por el border
            back.add(buttonAdd, BorderLayout.EAST);

            this.add(back, BorderLayout.NORTH);

            this.add(searchField);
        }

        private MaterialLabel labelHeader;
        private MaterialButtonAddEdit buttonAdd;
        private MaterialSearchField searchField;

        public void setHeader(String text) {
            labelHeader.setObject(text.toUpperCase());
        }

        public void addActionListenerButtonAdd(ActionListener listener) {
            buttonAdd.addActionListener(listener);
        }

        public String getSearchText() {
            return searchField.getSearchField().getText();
        }

        public void setSearchActionListener(ActionListener searchAction) {
            searchField.setSearchActionListener(searchAction);
        }
    }
}
