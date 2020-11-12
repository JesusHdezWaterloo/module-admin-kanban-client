package com.jhw.module.admin.kanban.consume.usecase_def;

import com.jhw.module.admin.kanban.core.domain.*;
import com.jhw.module.admin.kanban.core.usecase_def.*;

public interface ProyectoUseCaseConsume extends ProyectoUseCase {

    /**
     * Actualiza todas las ramas contra el repo online
     *
     * @param proyecto
     * @throws Exception
     */
    public void updateRemote(ProyectoDomain proyecto) throws Exception;

    /**
     * Revisa si un proyecto tiene al menos un remote
     *
     * @param proyecto
     * @throws Exception
     */
    public boolean hasRemote(ProyectoDomain proyecto);
}
