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
package com.root101.module.admin.kanban.consume.repo_impl;

import com.root101.module.admin.kanban.core.domain.TareaDomain;
import com.root101.module.admin.kanban.core.domain.ColumnaProyectVolatile;
import com.root101.module.admin.kanban.core.usecase_def.TareaUseCase;
import com.root101.module.admin.kanban.core.domain.MoveTarea;
import static com.root101.module.admin.kanban.rest.ModuleAdminKanbanRESTConstants.*;
import com.root101.module.util.rest_config.services.RESTHandler;
import com.root101.spring.client.ConsumerRepoTemplate;
import com.root101.spring.client.RestTemplateUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.client.RestOperations;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
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
    public List<TareaDomain> findByColumnaProyecto(ColumnaProyectVolatile.LightWeigth colProy) throws RuntimeException {
        Map<String, Object> map = new HashMap<>();
        map.put(COLUMNA, colProy.idColumna);
        map.put(PROYECTO, colProy.idProyecto);
        return RestTemplateUtils.getForList(template(), urlGeneral + TAREA_FIND_BY_COL_PROY_PATH, map, TareaDomain.class);
    }

    @Override
    public TareaDomain move(MoveTarea move) throws RuntimeException {
        return template().postForObject(urlGeneral + TAREA_MOVE_PATH, move, TareaDomain.class);
    }

}
