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
package com.root101.module.admin.kanban.ui.export;

import com.root101.swing.models.utils.DefaultExportableConfig;
import com.root101.module.admin.kanban.core.domain.TareaDomain;
import com.root101.module.admin.kanban.ui.tarea.TareaDetailViewHistorico;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class TareaExport extends DefaultExportableConfig<TareaDomain> {

    public static TareaExport from(TareaDetailViewHistorico detail) {
        return new TareaExport(detail);
    }

    public TareaExport(TareaDetailViewHistorico detail) {
        super(detail);
    }
/*
    @Override
    public Object[] getRowObjectExport(ProyectoDomain obj) {
        return new Object[]{
            obj.getNombreProyecto(),
            obj.getPrioridad(),
            obj.getFechaInicio(),
            obj.getUrlLocal(),
            KanbanSwingModule.proyectoUC.hasRemote(obj) ? obj.getUrlRepoOnline() : "",
            obj.getDescripcion()
        };
    }

    @Override
    public String[] getColumnNamesExport() {
        return new String[]{
            "Proyecto",
            "Prioridad",
            "Fecha de Inicio",
            "URL Local",
            "URL Online",
            "Descripción"
        };
    }

    @Override
    public void personalizeBuilder(ExcelListWriter.builder builder) {
        builder.updateValuesColumnCellStyle(2, (Workbook t, CellStyle u) -> {
            u.setDataFormat(t.createDataFormat().getFormat("dd-MMM-yyyy"));
            return u;
        });
    }*/
}
