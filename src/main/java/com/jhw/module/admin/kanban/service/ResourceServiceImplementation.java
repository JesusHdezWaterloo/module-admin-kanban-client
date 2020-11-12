package com.jhw.module.admin.kanban.service;

import com.clean.core.domain.services.Resource;
import com.clean.core.domain.services.ResourceBundleUtils;
import com.clean.core.domain.services.ResourceService;
import com.clean.core.domain.services.DefaultResourceBundleService;
import java.net.MalformedURLException;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class ResourceServiceImplementation implements ResourceService {

    public static final String RESOURCE_URL = "module_kanban";

    private final DefaultResourceBundleService resourceService;

    public static ResourceServiceImplementation init() {
        try {
            ResourceServiceImplementation res = new ResourceServiceImplementation();
            Resource.registerResourceService(res);
            return res;
        } catch (Exception e) {
        }
        return null;
    }

    private ResourceServiceImplementation() throws MalformedURLException {
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
