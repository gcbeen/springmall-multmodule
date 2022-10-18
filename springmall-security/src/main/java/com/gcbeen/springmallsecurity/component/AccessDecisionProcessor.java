package com.gcbeen.springmallsecurity.component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class AccessDecisionProcessor implements AccessDecisionVoter<FilterInvocation>  {

    @Autowired
    private IDynamicSecurityService dynamicSecurityService;

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    // Collection<ConfigAttribute> configAttributes 在配置文件中配置的过滤规则
    @Override
    public int vote(Authentication authentication, FilterInvocation object, Collection<ConfigAttribute> configAttributes) {
        assert authentication != null;
        assert object != null;
        Map<String, ConfigAttribute> map = dynamicSecurityService.loadDataSource();
        // 拿到当前请求uri
        String requestUrl = object.getRequestUrl();
        String method = object.getRequest().getMethod();
        log.debug("进入自定义鉴权投票器，URI : {} {}", method, requestUrl);
        // String key = requestUrl + ":" + method;
        PathMatcher pathMatcher = new AntPathMatcher();
        Set<String> keySet = map.keySet();
        
        List<String> keyList = keySet.stream()
            .filter(path -> pathMatcher.match(path, requestUrl))
            .limit(1)
            .collect(Collectors.toList());
        // 当接口未被配置权限时
        // 弃权
        if (keyList.size() < 1) {
            return ACCESS_ABSTAIN;
        }

        for (String key : keyList) {
            ConfigAttribute configAttribute = map.get(key);
            // 当接口未被配置权限时
            // 弃权
            if (configAttribute == null) {
                return ACCESS_ABSTAIN;
            }
            // 将 访问所需资源 和 用户拥有的资源 进行比对
            String needAuthority = configAttribute.getAttribute();
            log.info("needAuthority : " + needAuthority);
            for (GrantedAuthority grantedAuthority : authentication.getAuthorities() ) {
                log.info("grantedAuthority : " +grantedAuthority.getAuthority());
                if (needAuthority.trim().equals(grantedAuthority.getAuthority())) {
                    return ACCESS_GRANTED;
                }
            }
        }

        return ACCESS_DENIED;     
    }
    
}
