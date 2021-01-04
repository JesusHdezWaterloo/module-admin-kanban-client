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

import com.root101.module.admin.kanban.core.usecase_def.ColumnaUseCase;
import com.root101.module.admin.kanban.core.domain.ColumnaDomain;
import static com.root101.module.admin.kanban.rest.ModuleAdminKanbanRESTConstants.*;
import com.jhw.module.util.rest_config.services.RESTHandler;
import com.jhw.utils.spring.client.ConsumerRepoTemplate;
import org.springframework.web.client.RestOperations;

/**
 * 
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class ColumnaRepoImpl extends ConsumerRepoTemplate<ColumnaDomain> implements ColumnaUseCase {

    public ColumnaRepoImpl() {
        super(ColumnaDomain.class, RESTHandler.urlActualREST() + COLUMMA_GENERAL_PATH);
    }

    @Override
    protected RestOperations template() {
        return RESTHandler.OAuth2RestTemplate();
    }

    @Override
    public ColumnaDomain findFirst() throws Exception {
        return template().getForObject(urlGeneral + COLUMNA_FIND_FIRST_PATH, ColumnaDomain.class);
    }

    @Override
    public ColumnaDomain findLast() throws Exception {
        return template().getForObject(urlGeneral + COLUMNA_FIND_LAST_PATH, ColumnaDomain.class);
    }
}
