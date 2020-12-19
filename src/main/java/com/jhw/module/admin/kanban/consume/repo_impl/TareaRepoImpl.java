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
import com.jhw.utils.spring.client.RestTemplateUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.client.RestOperations;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class TareaRepoImpl extends ConsumerRepoTemplate<TareaDomain> implements TareaUseCase {

    public TareaRepoImpl() {
        super(TareaDomain.class, RESTHandler.urlActualREST() + TAREA_GENERAL_PATH);
    }

    @Override
    protected RestOperations template() {
        return RESTHandler.OAuth2RestTemplate();
    }

    @Override
    public List<TareaDomain> findByColumnaProyecto(ColumnaProyectVolatile.LightWeigth colProy) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put(COLUMNA, colProy.idColumna);
        map.put(PROYECTO, colProy.idProyecto);
        return RestTemplateUtils.getForList(template(), urlGeneral + TAREA_FIND_BY_COL_PROY_PATH, map, TareaDomain.class);
    }

    @Override
    public TareaDomain move(MoveTarea move) throws Exception {
        return template().postForObject(urlGeneral + TAREA_MOVE_PATH, move, TareaDomain.class);
    }

}
