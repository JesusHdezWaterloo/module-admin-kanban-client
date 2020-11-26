package com.jhw.module.admin.kanban.ui.proyecto;

import com.clean.core.app.services.ExceptionHandler;
import com.jhw.module.admin.kanban.core.domain.ProyectoDomain;
import com.jhw.module.admin.kanban.ui.module.KanbanModuleNavigator;
import com.jhw.module.admin.kanban.ui.module.KanbanSwingModule;
import com.jhw.swing.material.components.container.MaterialContainersFactory;
import com.jhw.swing.material.components.container.layout.HorizontalLayoutContainer;
import com.jhw.swing.material.components.labels.MaterialLabel;
import com.jhw.swing.material.components.labels.MaterialLabelsFactory;
import com.jhw.swing.material.components.table.Column;
import com.jhw.swing.material.components.table.editors_renders.component.ComponentCellRender;
import com.jhw.swing.material.standards.MaterialColors;
import com.jhw.swing.material.standards.MaterialIcons;
import com.jhw.swing.models.clean.CleanDetailCRUDDragDrop;
import com.jhw.swing.models.input.panels.ModelPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

/**
 *
 * @author Jesús Hernández Barrios (jhernandezb96@gmail.com)
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
    }

    @Override
    protected List<ProyectoDomain> getListUpdate() throws Exception {
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
        try {
            return KanbanSwingModule.proyectoUC.destroy(obj);
        } catch (Exception ex) {
            ExceptionHandler.handleException(ex);
        }
        return null;
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
