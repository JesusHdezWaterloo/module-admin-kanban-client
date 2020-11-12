package com.jhw.module.admin.kanban.ui.tarea;

import com.clean.core.app.services.ExceptionHandler;
import com.jhw.module.admin.kanban.core.domain.TareaDomain;
import com.jhw.module.admin.kanban.ui.module.KanbanModuleNavigator;
import com.jhw.module.admin.kanban.ui.module.KanbanSwingModule;
import com.jhw.swing.material.components.table.Column;
import com.jhw.swing.models.clean.CleanDetailCRUDDragDrop;
import com.jhw.swing.models.input.panels.ModelPanel;
import java.util.List;

/**
 *
 * @author Jesús Hernández Barrios (jhernandezb96@gmail.com)
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
    protected List<TareaDomain> getListUpdate() throws Exception {
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
