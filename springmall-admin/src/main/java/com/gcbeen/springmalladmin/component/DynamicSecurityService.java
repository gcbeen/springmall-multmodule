package com.gcbeen.springmalladmin.component;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.stereotype.Component;

import com.gcbeen.springmalladmin.service.IUmsResourceCacheService;
import com.gcbeen.springmallsecurity.component.IDynamicSecurityService;


// @Slf4j
@Component
public class DynamicSecurityService implements IDynamicSecurityService {

    @Autowired
    private IUmsResourceCacheService resourceCacheService;

    @Override
    public Map<String, ConfigAttribute> loadDataSource() {
       return resourceCacheService.getResourceMap();
        // Map<String, ConfigAttribute> map = new ConcurrentHashMap<>();
        // List<UmsResource> resourceList = resourceService.list(); 
        // for (UmsResource resource : resourceList) { 
        //     map.put(resource.getUrl(), new org.springframework.security.access.SecurityConfig(resource.getId() + ":" + resource.getName()));
        // }
        // return map;
    }
    
}
