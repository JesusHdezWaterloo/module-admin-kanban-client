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
import com.jhw.swing.material.components.button._MaterialButton;
import com.jhw.swing.material.injection.Background_Force_Foreground;
import com.jhw.swing.material.injection.Foreground_Force_Icon;
import com.jhw.swing.material.injection.MaterialSwingInjector;
import com.jhw.swing.material.standards.MaterialColors;
import com.jhw.swing.models.input.dialogs.DialogModelInput;
import com.jhw.swing.util.interfaces.BindableComponent;
import com.root101.utils.interfaces.Update;
import java.awt.Color;
import java.awt.event.ActionEvent;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
@Background_Force_Foreground
@Foreground_Force_Icon
public class PrioridadSingleDetail extends _MaterialButton implements Update, BindableComponent<PrioridadDomain> {
    
    public static PrioridadSingleDetail from(PrioridadDomain prioridad) {
        PrioridadSingleDetail p = MaterialSwingInjector.getImplementation(PrioridadSingleDetail.class);
        p.setObject(prioridad);
        return p;
    }
    
    private PrioridadDomain prioridad;
    
    @Deprecated
    public PrioridadSingleDetail() {
        addListeners();
    }
    
    @Override
    public void update() {
        this.setText(prioridad.getNombrePrioridad());
        this.setToolTipText(prioridad.getDescripcion());
        try {
            this.setBackground(new Color(prioridad.getColor()));
        } catch (Exception e) {
            this.setBackground(MaterialColors.TRANSPARENT);
        }
    }
    
    private void addListeners() {
        this.addActionListener((ActionEvent e) -> {
            new DialogModelInput(PrioridadSingleDetail.this, PrioridadInputView.fromModel(prioridad));
        });
    }
    
    @Override
    public PrioridadDomain getObject() {
        return prioridad;
    }
    
    @Override
    public void setObject(PrioridadDomain t) {
        this.prioridad = t;
        update();
    }
    
}
