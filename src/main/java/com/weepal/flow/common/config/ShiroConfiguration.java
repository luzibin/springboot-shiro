package com.weepal.flow.common.config;

import com.weepal.flow.common.security.MyShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {

    private Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);

    /**
     *  Shiro内置的FilterChain
     * 	    1、anon:所有url都都可以匿名访问
     *      2、authc: 需要认证才能进行访问
     *      3、user:配置记住我或认证通过可以访问
     * @param securityManager 管理器
     * @return
     */
    //设置 白/黑名单
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        logger.info("配置黑白名单");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(this.securityManager()); //获取SecurityManager
        shiroFilterFactoryBean.setLoginUrl("/login.html");
//        shiroFilterFactoryBean.setSuccessUrl("/index.html");

        //拦截器
        Map<String,String> filterChainDefinntionMap = new LinkedHashMap<>();
        //controller
        filterChainDefinntionMap.put("/wplogin","anon");
        //swagger
        filterChainDefinntionMap.put("/swagger-ui.html", "anon");
        filterChainDefinntionMap.put("/swagger-resources/**", "anon");
        filterChainDefinntionMap.put("/v2/api-docs", "anon");
        filterChainDefinntionMap.put("/webjars/springfox-swagger-ui/**", "anon");
        //static前端文件
        filterChainDefinntionMap.put("/static/**","anon");
        //logout（自带）
        filterChainDefinntionMap.put("/logout","logout");
        //黑名单
        filterChainDefinntionMap.put("/**","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinntionMap);
        return shiroFilterFactoryBean;
    }

    //设置 security Manager
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(this.myShiroRealm()); //设置用户/用户角色权限
        securityManager.setCacheManager(this.ehCacheManager());  //设置 缓存
        return securityManager;
    }

    //设置 缓存
    @Bean
    public EhCacheManager ehCacheManager(){
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:cache/ehcache-local.xml");
        return ehCacheManager;
    }

    //设置 用户/用户角色权限
    @Bean
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(this.hashedcredentialsMatcher());
        return myShiroRealm;
    }

    //设置 凭证匹配器
    @Bean
    public HashedCredentialsMatcher hashedcredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5"); ////散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(1);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }

    //开启shiro aop注解支持.
    //使用代理方式;所以需要开启代码支持;
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    //设置 自动代理
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);   //代理该类
        return defaultAdvisorAutoProxyCreator;
    }

    //处理 异常
  /*  @Bean(value = "simpleMappingExceptionResolver")
    public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver(){
        SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
        Properties properties = new Properties();
        properties.setProperty("DatabaseException","databaseError");//数据库异常处理
//        properties.setProperty("UnauthorizedException","403");      //未授权异常处理

        r.setExceptionMappings(properties);// None by default
        r.setDefaultErrorView("error");// No default
        r.setExceptionAttribute("ex");// Default is "exception"
        return r;
    }*/
}
