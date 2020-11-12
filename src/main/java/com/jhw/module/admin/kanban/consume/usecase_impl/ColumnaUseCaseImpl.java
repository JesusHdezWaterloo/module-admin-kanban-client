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

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class ColumnaUseCaseImpl extends DefaultCRUDUseCase<ColumnaDomain> implements ColumnaUseCaseConsume {

    private final ColumnaRepoImpl repoUC = KanbanConsumeCoreModule.getInstance().getImplementation(ColumnaRepoImpl.class);

    public ColumnaUseCaseImpl() {
        setRepo(repoUC);
    }

    @Override
    public ColumnaDomain findFirst() throws Exception {
        return repoUC.findFirst();
    }

    @Override
    public ColumnaDomain findLast() throws Exception {
        return repoUC.findLast();
    }

}
