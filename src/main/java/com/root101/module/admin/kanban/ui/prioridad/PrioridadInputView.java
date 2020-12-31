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
package com.root101.module.admin.kanban.ui.prioridad;

import com.jhw.module.admin.kanban.core.domain.PrioridadDomain;
import com.root101.module.admin.kanban.ui.module.KanbanSwingModule;
import com.jhw.swing.material.components.colorchooser.MaterialColorChooserFactory;
import com.jhw.swing.material.components.colorchooser.MaterialColorChooserIcon;
import com.jhw.swing.material.components.container.layout.VerticalLayoutContainer;
import com.jhw.swing.material.components.textarea.MaterialTextArea;
import com.jhw.swing.prepared.textarea.MaterialPreparedTextAreaFactory;
import com.jhw.swing.material.components.textfield.MaterialTextFactory;
import com.jhw.swing.material.components.textfield.MaterialTextFieldIcon;
import com.jhw.swing.material.standards.MaterialIcons;
import com.jhw.swing.models.clean.CleanCRUDInputView;
import com.jhw.swing.prepared.textfield.MaterialPreparedTextFactory;
import java.awt.Color;
import java.util.Map;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class PrioridadInputView extends CleanCRUDInputView<PrioridadDomain> {

    public static PrioridadInputView from() {
        return new PrioridadInputView(null);
    }

    public static PrioridadInputView fromModel(PrioridadDomain model) {
        return new PrioridadInputView(model);
    }

    private PrioridadInputView(PrioridadDomain model) {
        super(model, KanbanSwingModule.prioridadUC, PrioridadDomain.class);
        initComponents();
        update();
    }

    private void initComponents() {
        setHeader("Crear Prioridad", "Editar Prioridad");

        textFieldNombre = MaterialTextFactory.buildIcon();
        textFieldNombre.setHint("Nombre de la prioridad");
        textFieldNombre.setLabel("Prioridad");
        textFieldNombre.setIcon(MaterialIcons.PRIORITY_HIGH);

        textFieldValor = MaterialPreparedTextFactory.buildIntegerIcon(true);
        textFieldValor.setIcon(MaterialIcons.EXPOSURE_PLUS_1);
        textFieldNombre.setHint("Valor para comparar");
        textFieldValor.setLabel("Valor");

        colorChooser = MaterialColorChooserFactory.buildIcon();

        textAreaDescripcion = MaterialPreparedTextAreaFactory.buildDescripcion();

        VerticalLayoutContainer.builder vlc = VerticalLayoutContainer.builder();

        vlc.add(textFieldNombre);
        vlc.add(textFieldValor);
        vlc.add(colorChooser);
        vlc.add(textAreaDescripcion, true);

        this.setComponent(vlc.build());
    }

    // Variables declaration - do not modify
    private MaterialTextFieldIcon textFieldNombre;
    private MaterialTextFieldIcon<Integer> textFieldValor;
    private MaterialColorChooserIcon colorChooser;
    private MaterialTextArea textAreaDescripcion;
    // End of variables declaration

    @Override
    public void update() {
        super.update();
        if (getOldModel() != null) {
            this.colorChooser.setObject(new Color(getOldModel().getColor()));
        }
    }

    @Override
    public PrioridadDomain getNewModel() throws Exception {
        PrioridadDomain neww = super.getNewModel();
        neww.setColor(colorChooser.getObject().getRGB());
        return neww;
    }

    @Override
    public Map<String, Object> bindFields() {
        Map<String, Object> map = super.bindFields();
        map.put("nombrePrioridad", textFieldNombre);
        map.put("descripcion", textAreaDescripcion);
        map.put("valorComparable", textFieldValor);
        return map;
    }

}
