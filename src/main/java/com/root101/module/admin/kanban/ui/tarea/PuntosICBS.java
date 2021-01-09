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

import com.root101.module.admin.kanban.core.utils.FibonacciNumberUtils;
import com.root101.swing.models.input.icbs.InputComboBoxSelection;
import com.root101.swing.models.input.panels.ModelPanel;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class PuntosICBS extends InputComboBoxSelection<Integer> {

    public PuntosICBS() {
        setLabel("Puntos");
        setButtonNuevoVisibility(false);
    }

    @Override
    public List<Integer> getList() throws Exception {
        return Arrays.asList(FibonacciNumberUtils.generateFibboFixed());
    }

    @Override
    public ModelPanel<Integer> inputPanel() {
        return null;
    }

    @Override
    protected void addPropertyChange() {
    }

}
