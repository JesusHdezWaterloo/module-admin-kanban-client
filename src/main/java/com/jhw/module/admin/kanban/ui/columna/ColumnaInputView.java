package com.jhw.module.admin.kanban.ui.columna;

import com.jhw.module.admin.kanban.core.domain.ColumnaDomain;
import com.jhw.module.admin.kanban.ui.module.KanbanSwingModule;
import com.jhw.swing.material.components.container.layout.VerticalLayoutContainer;
import com.jhw.swing.material.components.textarea.MaterialTextArea;
import com.jhw.swing.prepared.textarea.MaterialPreparedTextAreaFactory;
import com.jhw.swing.material.components.textfield.MaterialTextFactory;
import com.jhw.swing.material.components.textfield.*;
import com.jhw.swing.material.standards.MaterialIcons;
import com.jhw.swing.models.clean.CleanCRUDInputView;
import java.util.Map;

/**
 *
 * @author Jesús Hernández Barrios (jhernandezb96@gmail.com)
 */
public class ColumnaInputView extends CleanCRUDInputView<ColumnaDomain> {

    public static ColumnaInputView from() {
        return new ColumnaInputView(null);
    }

    public static ColumnaInputView fromModel(ColumnaDomain model) {
        return new ColumnaInputView(model);
    }

    private ColumnaInputView(ColumnaDomain model) {
        super(model, KanbanSwingModule.columnaUC, ColumnaDomain.class);
        initComponents();
        update();
    }

    private void initComponents() {
        setHeader("Crear Columna", "Editar Columna");

        textFieldNombre = MaterialTextFactory.buildIcon();
        textFieldNombre.setHint("Nombre de la columna");
        textFieldNombre.setLabel("Columna");
        textFieldNombre.setIcon(MaterialIcons.PRIORITY_HIGH);
        
        textFieldPosicion = MaterialTextFactory.build();
        textFieldPosicion.setHint("Posición de la columna");
        textFieldPosicion.setLabel("Posición");
        
        textAreaDescripcion = MaterialPreparedTextAreaFactory.buildDescripcion();

        VerticalLayoutContainer.builder vlc = VerticalLayoutContainer.builder();

        vlc.add(textFieldNombre);
        vlc.add(textFieldPosicion);
        vlc.add(textAreaDescripcion, true);

        this.setComponent(vlc.build());
    }

    // Variables declaration - do not modify
    private MaterialTextFieldIcon textFieldNombre;
    private MaterialTextField textFieldPosicion;
    private MaterialTextArea textAreaDescripcion;
    // End of variables declaration

    @Override
    public Map<String, Object> bindFields() {
        Map<String, Object> map = super.bindFields();
        map.put("nombreColumna", textFieldNombre);
        map.put("posicion", textFieldPosicion);
        map.put("descripcion", textAreaDescripcion);
        return map;
    }

}
