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
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class PrioridadRepoImpl extends ConsumerRepoTemplate<PrioridadDomain> implements PrioridadUseCase {

    public PrioridadRepoImpl() {
        super(RESTHandler.restTemplate(), PrioridadDomain.class, RESTHandler.urlActualREST() + PRIORIDAD_GENERAL_PATH);
    }

    @Override
    public List<PrioridadDomain> findAll(String searchText) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put(SEARCH, searchText);
        return RestTemplateUtils.getForList(template, urlGeneral + PRIORIDAD_FIND_ALL_SEARCH_PATH, map, PrioridadDomain.class);
    }

}
