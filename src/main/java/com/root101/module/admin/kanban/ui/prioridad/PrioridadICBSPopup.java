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

import com.root101.module.admin.kanban.core.domain.PrioridadDomain;
import com.root101.module.admin.kanban.ui.module.KanbanSwingModule;
import com.root101.swing.material.standards.MaterialFontRoboto;
import com.root101.swing.material.standards.MaterialIcons;
import com.root101.swing.models.input.panels.ModelPanel;
import com.root101.swing.models.input.popup_selection.InputPopupSelection;
import com.root101.swing.util.AbstractActionUtils;
import com.root101.swing.util.Utils;
import com.root101.swing.derivable_icons.IconTTF;
import java.awt.Color;
import java.util.List;
import javax.swing.Action;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class PrioridadICBSPopup extends InputPopupSelection<PrioridadDomain> {

    public static final String TEXT = "Prioridad";

    public PrioridadICBSPopup() {
        getComponent().setIcon(MaterialIcons.PEOPLE);
        getComponent().setText(TEXT);
    }

    @Override
    public List<PrioridadDomain> getList() throws Exception {
        return KanbanSwingModule.prioridadUC.findAll();
    }

    @Override
    protected Action convert(PrioridadDomain obj) {
        Color c = new Color(obj.getColor());
        Action a = AbstractActionUtils.from(
                obj.abreviatura(),
                IconTTF.extractIcon(MaterialFontRoboto.BOLD,
                        String.valueOf(obj.getValorComparable()),
                        Utils.getForegroundAccording(c),
                        20f));
        a.putValue(AbstractActionUtils.KEY_BACKGROUND, c);
        a.putValue(Action.SHORT_DESCRIPTION, obj.getNombrePrioridad());
        return a;
    }

    @Override
    public ModelPanel<PrioridadDomain> inputPanel() {//input para el agregar
        return PrioridadInputView.from();
    }

    @Override
    public void select(PrioridadDomain cargo) {//cambios visuales para cuando se selecciona
        //getComponent().getButton().setIcon(MATERIAL_ICONS_EXAMPLE.getRandomIcon());
        getComponent().setText(TEXT + " (" + cargo + ")");
        getComponent().getButton().setBackground(new Color(cargo.getColor()));
    }

    @Override
    protected void addPropertyChange() {
        KanbanSwingModule.prioridadUC.addPropertyChangeListener(this);
    }
}
