package com.shiro.sjwtm.shiro;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public SecurityManager securityManager(ShiroRealm shiroRealm) {
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        defaultSecurityManager.setRealm(shiroRealm);
        return defaultSecurityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map filterMap = new HashMap();
        filterMap.put("jwt", new ShiroFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        Map urlMap = new HashMap();
        urlMap.put("/user/login", "anon");
        urlMap.put("/unauthorized/**", "anon");
        urlMap.put("/swagger-ui.html", "anon");
        urlMap.put("/swagger-resources/**", "anon");
        urlMap.put("/images/**", "anon");
        urlMap.put("/configuration/**", "anon");
        urlMap.put("/v2/api-docs/**", "anon");
        urlMap.put("/webjars/springfox-swagger-ui/**", "anon");
        urlMap.put("/**", "jwt");


        shiroFilterFactoryBean.setFilterChainDefinitionMap(urlMap);

        shiroFilterFactoryBean.setLoginUrl("/user/login");
        shiroFilterFactoryBean.setSuccessUrl("/user/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("error");
        return shiroFilterFactoryBean;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
