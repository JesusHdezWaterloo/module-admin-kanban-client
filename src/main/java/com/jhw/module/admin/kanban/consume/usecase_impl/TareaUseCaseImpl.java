/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.admin.kanban.consume.usecase_impl;

import com.clean.core.app.usecase.*;
import com.jhw.module.admin.kanban.consume.module.KanbanConsumeCoreModule;
import com.jhw.module.admin.kanban.consume.repo_impl.*;
import com.jhw.module.admin.kanban.consume.usecase_def.*;
import com.jhw.module.admin.kanban.core.domain.*;
import java.util.List;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class TareaUseCaseImpl extends DefaultCRUDUseCase<TareaDomain> implements TareaUseCaseConsume {

    private final TareaRepoImpl repoUC = KanbanConsumeCoreModule.getInstance().getImplementation(TareaRepoImpl.class);

    public TareaUseCaseImpl() {
        setRepo(repoUC);
    }

    @Override
    public List<TareaDomain> findByColumnaProyecto(ColumnaProyectVolatile.LightWeigth colProy) throws Exception {
        return repoUC.findByColumnaProyecto(colProy);
    }

    @Override
    public TareaDomain move(MoveTarea move) throws Exception {
        TareaDomain tarea = repoUC.move(move);
        firePropertyChange("edit", null, tarea);
        return tarea;
    }

}
