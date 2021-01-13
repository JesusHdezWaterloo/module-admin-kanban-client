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
package com.root101.module.admin.kanban.consume.usecase_def;

import com.root101.module.admin.kanban.core.usecase_def.ProyectoUseCase;
import com.root101.module.admin.kanban.core.domain.ProyectoDomain;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public interface ProyectoUseCaseConsume extends ProyectoUseCase {

    /**
     * Actualiza todas las ramas contra el repo online
     *
     * @param proyecto
     * @throws RuntimeException
     */
    public void updateRemote(ProyectoDomain proyecto) throws RuntimeException;

    /**
     * Revisa si un proyecto tiene al menos un remote
     *
     * @param proyecto
     * @return
     */
    public boolean hasRemote(ProyectoDomain proyecto);

    public void irACarpeta(ProyectoDomain proyecto);

    public void irARepoOnline(ProyectoDomain proyecto);

    public void copiarURLLocal(ProyectoDomain proyecto);
}
