package com.jhw.module.admin.kanban.ui.proyecto;

import com.jhw.module.admin.kanban.core.domain.ProyectoDomain;
import com.jhw.module.admin.kanban.ui.module.KanbanSwingModule;
import com.jhw.swing.material.components.container.layout.VerticalLayoutContainer;
import com.jhw.swing.material.components.datepicker.MaterialDatePickerIcon;
import com.jhw.swing.material.components.datepicker.MaterialDatePickersFactory;
import com.jhw.swing.material.components.textarea.MaterialTextArea;
import com.jhw.swing.prepared.textarea.MaterialPreparedTextAreaFactory;
import com.jhw.swing.material.components.textfield.MaterialTextFactory;
import com.jhw.swing.material.components.textfield.MaterialTextFieldIcon;
import com.jhw.swing.material.components.toggle.MaterialToggleButton;
import com.jhw.swing.material.components.toggle.MaterialToggleFactory;
import com.jhw.swing.material.standards.MaterialIcons;
import com.jhw.swing.models.clean.CleanCRUDInputView;
import com.jhw.swing.prepared.textfield.MaterialPreparedTextFactory;
import java.util.Map;

/**
 *
 * @author Jesús Hernández Barrios (jhernandezb96@gmail.com)
 */
public class ProyectoInputView extends CleanCRUDInputView<ProyectoDomain> {

    public static ProyectoInputView from() {
        return new ProyectoInputView(null);
    }

    public static ProyectoInputView fromModel(ProyectoDomain model) {
        return new ProyectoInputView(model);
    }

    private ProyectoInputView(ProyectoDomain model) {
        super(model, KanbanSwingModule.proyectoUC, ProyectoDomain.class);
        initComponents();
        update();
    }

    private void initComponents() {
        setHeader("Crear Proyecto", "Editar Proyecto");

        textFieldNombre = MaterialTextFactory.buildIcon();
        textFieldNombre.setHint("Nombre del proyecto");
        textFieldNombre.setLabel("Proyecto");
        textFieldNombre.setIcon(MaterialIcons.PRIORITY_HIGH);

        datePickerInicio = MaterialDatePickersFactory.buildIcon();
        datePickerInicio.setHint("Fecha de inicio del proyecto");
        datePickerInicio.setLabel("Inicio");

        buttonKanban = MaterialToggleFactory.buildCheckBox();
        buttonKanban.setText("Usar Kanban");

        textFieldPrioridad = MaterialPreparedTextFactory.buildIntegerIcon();
        textFieldPrioridad.setLabel("Prioridad");
        textFieldPrioridad.setHint("Prioridad del proyecto");

        textFieldUrlLocal = MaterialTextFactory.buildIcon();
        textFieldUrlLocal.setLabel("URL local");

        textFieldUrlRepoOnline = MaterialTextFactory.buildIcon();
        textFieldUrlRepoOnline.setLabel("URL repo Online");

        textAreaDescripcion = MaterialPreparedTextAreaFactory.buildDescripcion();

        VerticalLayoutContainer.builder vlc = VerticalLayoutContainer.builder(500);

        vlc.add(textFieldNombre);
        vlc.add(datePickerInicio);
        vlc.add(buttonKanban);
        vlc.add(textFieldPrioridad);
        vlc.add(textFieldUrlLocal);
        vlc.add(textFieldUrlRepoOnline);
        vlc.add(textAreaDescripcion, true);

        this.setComponent(vlc.build());
    }

    // Variables declaration - do not modify
    private MaterialTextFieldIcon textFieldNombre;
    private MaterialDatePickerIcon datePickerInicio;
    private MaterialToggleButton buttonKanban;
    private MaterialTextFieldIcon textFieldPrioridad;
    private MaterialTextFieldIcon textFieldUrlLocal;
    private MaterialTextFieldIcon textFieldUrlRepoOnline;
    private MaterialTextArea textAreaDescripcion;
    // End of variables declaration

    @Override
    public Map<String, Object> bindFields() {
        Map<String, Object> map = super.bindFields();
        map.put("nombreProyecto", textFieldNombre);
        map.put("fechaInicio", datePickerInicio);
        map.put("kanban", buttonKanban);
        map.put("prioridad", textFieldPrioridad);
        map.put("urlLocal", textFieldUrlLocal);
        map.put("urlRepoOnline", textFieldUrlRepoOnline);
        map.put("descripcion", textAreaDescripcion);
        return map;
    }

}
