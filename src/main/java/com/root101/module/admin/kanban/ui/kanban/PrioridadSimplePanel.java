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

import com.root101.module.admin.kanban.core.domain.PrioridadDomain;
import com.jhw.swing.material.components.container.panel._MaterialPanelComponent;
import com.jhw.swing.material.components.labels.MaterialLabel;
import com.jhw.swing.material.components.labels._MaterialLabel;
import com.jhw.swing.util.Utils;
import com.jhw.swing.util.interfaces.BindableComponent;
import com.root101.utils.interfaces.Update;
import java.awt.Color;
import javax.swing.SwingConstants;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class PrioridadSimplePanel extends _MaterialPanelComponent implements Update, BindableComponent<PrioridadDomain> {
    
    public static PrioridadSimplePanel from() {
        return new PrioridadSimplePanel();
    }
    
    private PrioridadDomain prioridad;
    
    public PrioridadSimplePanel() {
        initComponents();
    }
    
    private void initComponents() {
        setGap(5);
        labelPrioridad = new _MaterialLabel();
        labelPrioridad.setHorizontalAlignment(SwingConstants.CENTER);
        
        this.add(labelPrioridad);
        
    }
    private MaterialLabel labelPrioridad;
    
    public void setPrioridad(PrioridadDomain prioridad) {
        this.prioridad = prioridad;
    }
    
    @Override
    public void update() {
        Color back = new Color(prioridad.getColor());
        
        labelPrioridad.setBackground(back);
        labelPrioridad.setForeground(Utils.getForegroundAccording(labelPrioridad.getBackground()));
        labelPrioridad.setObject(prioridad.getNombrePrioridad());
        
        this.setToolTipText(prioridad.getDescripcion());
        
        this.setBackground(back);
    }
    
    @Override
    public PrioridadDomain getObject() {
        return prioridad;
    }
    
    @Override
    public void setObject(PrioridadDomain t) {
        prioridad = t;
        update();
    }
}
