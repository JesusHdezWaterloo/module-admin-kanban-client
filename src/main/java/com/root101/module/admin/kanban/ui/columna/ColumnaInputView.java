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
package com.root101.module.admin.kanban.ui.columna;

import com.jhw.module.admin.kanban.core.domain.ColumnaDomain;
import com.root101.module.admin.kanban.ui.module.KanbanSwingModule;
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
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
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
