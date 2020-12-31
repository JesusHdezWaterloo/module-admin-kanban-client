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
package com.root101.module.admin.kanban.ui.tarea;

import com.jhw.module.admin.kanban.core.domain.ColumnaProyectVolatile;
import com.jhw.module.admin.kanban.core.domain.TareaDomain;
import com.root101.module.admin.kanban.ui.columna.ColumnaICBS;
import com.root101.module.admin.kanban.ui.module.KanbanSwingModule;
import com.root101.module.admin.kanban.ui.prioridad.PrioridadICBSPopup;
import com.root101.module.admin.kanban.ui.proyecto.ProyectoICBS;
import com.jhw.swing.material.components.container.layout.*;
import com.jhw.swing.material.components.textarea.MaterialTextArea;
import com.jhw.swing.material.components.textfield.*;
import com.jhw.swing.material.standards.MaterialIcons;
import com.jhw.swing.models.clean.CleanCRUDInputView;
import com.jhw.swing.prepared.textarea.MaterialPreparedTextAreaFactory;
import java.util.Map;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class TareaInputView extends CleanCRUDInputView<TareaDomain> {

    public static TareaInputView fromColumna(ColumnaProyectVolatile colProy) {
        TareaInputView tareaIC = from();
        tareaIC.columnaICBS.setObject(colProy.getColumna());
        tareaIC.proyectoICBS.setObject(colProy.getProyecto());
        return tareaIC;
    }

    public static TareaInputView from() {
        return new TareaInputView(null);
    }

    public static TareaInputView fromModel(TareaDomain model) {
        return new TareaInputView(model);
    }

    private TareaInputView(TareaDomain model) {
        super(model, KanbanSwingModule.tareaUC, TareaDomain.class);
        initComponents();
        update();
    }

    private void initComponents() {
        setHeader("Crear Tarea", "Editar Tarea");

        textFieldNombre = MaterialTextFactory.buildIcon();
        textFieldNombre.setHint("Nombre de la tarea");
        textFieldNombre.setLabel("Tarea");

        textFieldCodigo = MaterialTextFactory.buildIcon();
        textFieldCodigo.setHint("Código");
        textFieldCodigo.setLabel("Código");
        textFieldCodigo.setIcon(MaterialIcons.PRIORITY_HIGH);

        puntosICBS = new PuntosICBS();
        puntosICBS.setHint("Puntos de la tarea");
        puntosICBS.setLabel("Puntos");

        HorizontalLayoutContainer.builder hlc = HorizontalLayoutContainer.builder();

        hlc.add(textFieldCodigo);
        hlc.add(HorizontalLayoutComponent.builder(puntosICBS).gapLeft(5).build());

        prioridadICBS = new PrioridadICBSPopup();
        columnaICBS = new ColumnaICBS();
        proyectoICBS = new ProyectoICBS();
        //textAreaDescripcion = MaterialPreparedTextAreaFactory.buildDescripcion();

        textAreaDescripcion = MaterialPreparedTextAreaFactory.buildDescripcion();

        VerticalLayoutContainer.builder vlc = VerticalLayoutContainer.builder();
        vlc.add(textFieldNombre);
        vlc.add(hlc.build());
        vlc.add(proyectoICBS);
        vlc.add(columnaICBS);
        vlc.add(prioridadICBS);
        vlc.add(textAreaDescripcion, true);

        this.setComponent(vlc.build());
    }

    // Variables declaration - do not modify
    private MaterialTextFieldIcon<String> textFieldNombre;
    private MaterialTextFieldIcon textFieldCodigo;
    private PuntosICBS puntosICBS;
    private ColumnaICBS columnaICBS;
    private ProyectoICBS proyectoICBS;
    private PrioridadICBSPopup prioridadICBS;
    private MaterialTextArea textAreaDescripcion;
    // End of variables declaration

    @Override
    public void update() {
        super.update();
        if (getOldModel() != null) {
            proyectoICBS.setEnabled(false);
        }
    }

    @Override
    public Map<String, Object> bindFields() {
        Map<String, Object> map = super.bindFields();
        map.put("nombreTarea", textFieldNombre);
        map.put("codigoTarea", textFieldCodigo);
        map.put("prioridadFk", prioridadICBS);
        map.put("puntos", puntosICBS);
        map.put("columnaFk", columnaICBS);
        map.put("proyectoFk", proyectoICBS);
        map.put("descripcion", textAreaDescripcion);
        return map;
    }

}
