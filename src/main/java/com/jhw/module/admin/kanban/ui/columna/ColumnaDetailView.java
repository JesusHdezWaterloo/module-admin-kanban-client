package com.jhw.module.admin.kanban.ui.columna;

import com.clean.core.app.services.ExceptionHandler;
import com.jhw.module.admin.kanban.core.domain.ColumnaDomain;
import com.jhw.module.admin.kanban.ui.module.KanbanModuleNavigator;
import com.jhw.module.admin.kanban.ui.module.KanbanSwingModule;
import com.jhw.swing.models.detail._MaterialPanelDetail;
import com.jhw.swing.material.components.table.Column;
import com.jhw.swing.models.input.dialogs.DialogModelInput;

/**
 *
 * @author Jesús Hernández Barrios (jhernandezb96@gmail.com)
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
        try {
            setCollection(KanbanSwingModule.columnaUC.findAll());
        } catch (Exception e) {
            ExceptionHandler.handleException(e);
        }
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
        try {
            return KanbanSwingModule.columnaUC.destroy(obj);
        } catch (Exception ex) {
            ExceptionHandler.handleException(ex);
        }
        return null;
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
