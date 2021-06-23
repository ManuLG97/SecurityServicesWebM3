/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package securityservices.userinterface.web.restadapters;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author sandr
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(securityservices.userinterface.web.restadapters.ClientsResource.class);
        resources.add(securityservices.userinterface.web.restadapters.EquipmentResource.class);
        resources.add(securityservices.userinterface.web.restadapters.EquipmentfileResource.class);
        resources.add(securityservices.userinterface.web.restadapters.OrderResource.class);
        resources.add(securityservices.userinterface.web.restadapters.OrderfileResource.class);
        resources.add(securityservices.userinterface.web.restadapters.ServiceResource.class);
        resources.add(securityservices.userinterface.web.restadapters.ServicefileResource.class);
    }
    
}
