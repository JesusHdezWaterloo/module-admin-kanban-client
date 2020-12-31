/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.admin.kanban.consume.usecase_impl;

import com.root101.clean.core.app.usecase.DefaultCRUDUseCase;
import com.jhw.module.admin.kanban.consume.module.KanbanConsumeCoreModule;
import com.jhw.module.admin.kanban.consume.repo_impl.*;
import com.jhw.module.admin.kanban.consume.usecase_def.*;
import com.jhw.module.admin.kanban.core.domain.*;
import java.util.List;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class PrioridadUseCaseImpl extends DefaultCRUDUseCase<PrioridadDomain> implements PrioridadUseCaseConsume {

    private final PrioridadRepoImpl repoUC = KanbanConsumeCoreModule.getInstance().getImplementation(PrioridadRepoImpl.class);

    public PrioridadUseCaseImpl() {
        setRepo(repoUC);
    }

    @Override
    public List<PrioridadDomain> findAll(String searchText) throws Exception {
        return repoUC.findAll(searchText);
    }

}
