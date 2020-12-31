package com.jhw.module.admin.kanban.service;

import com.root101.clean.core.domain.services.ResourceHandler;
import com.root101.clean.core.domain.services.ResourceBundleUtils;
import com.root101.clean.core.domain.services.ResourceService;
import com.root101.clean.core.domain.services.DefaultResourceBundleService;
import java.net.MalformedURLException;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class ResourceServiceClientImplementation implements ResourceService {

    public static final String RESOURCE_URL = "module_kanban_client";

    private final DefaultResourceBundleService resourceService;

    public static ResourceServiceClientImplementation init() {
        try {
            ResourceServiceClientImplementation res = new ResourceServiceClientImplementation();
            ResourceHandler.registerResourceService(res);
            return res;
        } catch (Exception e) {
        }
        return null;
    }

    private ResourceServiceClientImplementation() throws MalformedURLException {
        resourceService = new DefaultResourceBundleService(
                ResourceBundleUtils.fromInternalFile(RESOURCE_URL,
                        ResourceBundleUtils.SPANISH));
    }

    @Override
    public String getString(String string) {
        return resourceService.getString(string);
    }

    @Override
    public Object getObject(String string) {
        return resourceService.getObject(string);
    }

    @Override
    public boolean contain(String string) {
        return resourceService.contain(string);
    }
}
