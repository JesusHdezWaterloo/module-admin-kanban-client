package com.jhw.module.admin.kanban.consume.module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.jhw.module.admin.kanban.consume.usecase_def.*;
import com.jhw.module.admin.kanban.consume.usecase_impl.*;
import com.jhw.module.admin.kanban.core.usecase_def.*;

/**
 * Configuracion del injection del modulo de PlanTrabajo-consume-core.
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class KanbanConsumeCoreInjectionConfig extends AbstractModule {

    @Override
    protected void configure() {
        bind(ColumnaUseCase.class).to(ColumnaUseCaseImpl.class).in(Singleton.class);
        bind(ColumnaUseCaseConsume.class).to(ColumnaUseCaseImpl.class).in(Singleton.class);

        bind(PrioridadUseCase.class).to(PrioridadUseCaseImpl.class).in(Singleton.class);
        bind(PrioridadUseCaseConsume.class).to(PrioridadUseCaseImpl.class).in(Singleton.class);

        bind(ProyectoUseCase.class).to(ProyectoUseCaseImpl.class).in(Singleton.class);
        bind(ProyectoUseCaseConsume.class).to(ProyectoUseCaseImpl.class).in(Singleton.class);

        bind(ProyectoUseCase.class).to(ProyectoUseCaseImpl.class).in(Singleton.class);
        bind(ProyectoUseCaseConsume.class).to(ProyectoUseCaseImpl.class).in(Singleton.class);
    }

}
