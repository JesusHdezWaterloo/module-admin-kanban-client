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

import com.root101.module.admin.kanban.core.domain.ColumnaDomain;
import com.root101.module.admin.kanban.ui.module.KanbanModuleNavigator;
import com.root101.module.admin.kanban.ui.module.KanbanSwingModule;
import com.root101.swing.models.input.icbs.InputComboBoxSelection;
import com.root101.swing.models.input.panels.ModelPanel;
import java.util.List;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class ColumnaICBS extends InputComboBoxSelection<ColumnaDomain> {

    public ColumnaICBS() {
        setLabel("Columna");
        setIcon(KanbanModuleNavigator.ICON_PROYECTO);
    }

    @Override
    public List<ColumnaDomain> getList() throws RuntimeException {
        return KanbanSwingModule.columnaUC.findAll();
    }

    @Override
    public ModelPanel<ColumnaDomain> inputPanel() {
        return ColumnaInputView.from();
    }

    @Override
    protected void addPropertyChange() {
        KanbanSwingModule.columnaUC.addPropertyChangeListener(this);
    }
}
