/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.admin.kanban.consume.repo_impl;

import static com.jhw.module.admin.kanban.core.ModuleAdminKanbanConstants.*;
import com.jhw.module.admin.kanban.core.domain.*;
import com.jhw.module.admin.kanban.core.usecase_def.*;
import com.jhw.module.util.rest_config.services.RESTHandler;
import com.jhw.utils.spring.client.ConsumerRepoTemplate;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class ProyectoRepoImpl extends ConsumerRepoTemplate<ProyectoDomain> implements ProyectoUseCase {

    public ProyectoRepoImpl() {
        super(RESTHandler.restTemplate(), ProyectoDomain.class, RESTHandler.urlActualREST() + PROYECTO_GENERAL_PATH);
    }

}
