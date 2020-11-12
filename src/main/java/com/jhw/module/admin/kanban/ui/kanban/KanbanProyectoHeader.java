/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.admin.kanban.ui.kanban;

import com.jhw.module.admin.kanban.core.domain.ProyectoDomain;
import com.jhw.swing.material.components.container.panel._MaterialPanelComponent;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class KanbanProyectoHeader extends _MaterialPanelComponent {

    public static KanbanProyectoHeader from(ProyectoDomain proyecto) {
        return new KanbanProyectoHeader(proyecto);
    }

    private final ProyectoDomain proyecto;

    public KanbanProyectoHeader(ProyectoDomain proyecto) {
        this.proyecto = proyecto;
        initComponents();
        addListeners();
    }

    private void initComponents() {
    }

    private void addListeners() {
    }
}
