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
package com.root101.module.admin.kanban.ui.kanban;

import com.root101.clean.core.app.services.ExceptionHandler;
import com.root101.module.admin.kanban.core.domain.ColumnaDomain;
import com.root101.module.admin.kanban.core.domain.ColumnaProyectVolatile;
import com.root101.module.admin.kanban.core.domain.ProyectoDomain;
import com.root101.module.admin.kanban.core.domain.TareaDomain;
import com.root101.module.admin.kanban.ui.module.KanbanSwingModule;
import com.root101.swing.material.components.container.MaterialContainersFactory;
import com.root101.swing.material.components.container.panel._PanelGradient;
import com.root101.swing.models.utils.UpdateListener;
import com.root101.utils.interfaces.Update;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
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
        panelColumnas.removeAll();//quito todo

        //busco las columnas
        List<ColumnaDomain> columnas = KanbanSwingModule.columnaUC.findAll();

        //pongo el layout en dependencia de las columnas
        panelColumnas.setLayout(new GridLayout(1, columnas.size()));

        //relleno las columnas
        for (ColumnaDomain c : columnas) {
            panelColumnas.add(KanbanColumn.from(new ColumnaProyectVolatile(proyecto, c)));
        }
    }

    private void addPropertyListeners() {
        KanbanSwingModule.tareaUC.addPropertyChangeListener(updList);
        KanbanSwingModule.columnaUC.addPropertyChangeListener(updList);
    }
}
