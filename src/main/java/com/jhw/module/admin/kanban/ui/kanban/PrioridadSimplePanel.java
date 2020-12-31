/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.admin.kanban.ui.kanban;

import com.jhw.module.admin.kanban.core.domain.PrioridadDomain;
import com.jhw.swing.material.components.container.panel._MaterialPanelComponent;
import com.jhw.swing.material.components.labels.MaterialLabel;
import com.jhw.swing.material.components.labels.MaterialLabelsFactory;
import com.jhw.swing.material.components.labels._MaterialLabel;
import com.jhw.swing.util.Utils;
import com.jhw.swing.util.interfaces.BindableComponent;
import com.root101.utils.interfaces.Update;
import java.awt.Color;
import javax.swing.SwingConstants;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
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
