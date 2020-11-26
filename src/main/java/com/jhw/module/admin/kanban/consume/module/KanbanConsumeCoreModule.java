package com.jhw.module.admin.kanban.consume.module;

import com.clean.core.app.modules.DefaultAbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.jhw.module.admin.kanban.service.ResourceServiceImplementation;

/**
 * Modulo de Kanban-consume-core.
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class KanbanConsumeCoreModule extends DefaultAbstractModule {

    private final Injector inj = Guice.createInjector(new KanbanConsumeCoreInjectionConfig());

    private static KanbanConsumeCoreModule INSTANCE;

    public static KanbanConsumeCoreModule getInstance() {
        if (INSTANCE == null) {
            init();
        }
        return INSTANCE;
    }

    /**
     * Es responsabilidad del init saber que tiene que inicializar
     *
     * @return
     */
    private static void init() {
        INSTANCE = new KanbanConsumeCoreModule();
        ResourceServiceImplementation.init();
    }

    @Override
    protected <T> T getOwnImplementation(Class<T> type) {
        return inj.getInstance(type);
    }

    @Override
    public String getModuleName() {
        return "Kanban Consume Core Module";
    }

}
