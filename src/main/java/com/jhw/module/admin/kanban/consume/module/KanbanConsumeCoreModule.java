package com.jhw.module.admin.kanban.consume.module;

import com.clean.core.app.modules.DefaultAbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

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
            throw new NullPointerException("El modulo de Kanban Consume-Core no se ha inicializado");
        }
        return INSTANCE;
    }

    public static KanbanConsumeCoreModule init() {
        INSTANCE = new KanbanConsumeCoreModule();
        return getInstance();
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
