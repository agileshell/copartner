package com.insoul.ti.shiro;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 *
 * @version 1.0.0
 * @since 2015年9月21日 上午11:33:52
 */
public class SimpleFilterFactoryBean extends ShiroFilterFactoryBean implements ApplicationContextAware, InitializingBean {

    private static final String LINE = "\n";

    private static final String EQ = "=";

    private static final String ROOT_SUFFIX = "/";

    protected final Logger log = LoggerFactory.getLogger(getClass());

    protected ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Override
    public final void afterPropertiesSet() throws Exception {
        log.error("Enter SimpleFilterFactoryBean...");
        final Map<String, String> filterChainDefinitionMap = new HashMap<String, String>();
        Map<String, HandlerMapping> requestMappings = BeanFactoryUtils.beansOfTypeIncludingAncestors(context, HandlerMapping.class, true, false);
        for (HandlerMapping handlerMapping : requestMappings.values()) {
            if (!(handlerMapping instanceof RequestMappingHandlerMapping)) {
                log.error("handlerMapping : " + handlerMapping);
                continue;
            }
            RequestMappingHandlerMapping requestMappingHandlerMapping = (RequestMappingHandlerMapping) handlerMapping;
            Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
            for (Map.Entry<RequestMappingInfo, HandlerMethod> e : handlerMethods.entrySet()) {
                RequestMappingInfo requestMappingInfo = e.getKey();
                HandlerMethod mappingInfoValue = e.getValue();
                Class<?> handlerType = mappingInfoValue.getBeanType();
                RequestMapping requestMapping = AnnotationUtils.findAnnotation(handlerType, RequestMapping.class);
                Permission shiroTypeFilter = AnnotationUtils.findAnnotation(handlerType, Permission.class);
                if (requestMapping != null && shiroTypeFilter != null) {
                    String[] urlPatterns = requestMapping.value();
                    if (ArrayUtils.isEmpty(urlPatterns)) {
                        continue;
                    }
                    String filterName = shiroTypeFilter.value();
                    for (String urlPattern : urlPatterns) {
                        if (StringUtils.endsWith(urlPattern, ROOT_SUFFIX)) {
                            log.error("[Shiro URL Filter] {}**={}", urlPattern, filterName);
                            filterChainDefinitionMap.put(urlPattern + "**", filterName);
                            continue;
                        }
                        log.error("[Shiro URL Filter] {}/**={}", urlPattern, filterName);
                        filterChainDefinitionMap.put(urlPattern + "/**", filterName);
                    }
                    continue;
                }
                Method handlerMethod = mappingInfoValue.getMethod();
                Permission shiroMethodFilter = AnnotationUtils.getAnnotation(handlerMethod, Permission.class);
                if (shiroMethodFilter == null) {
                    continue;
                }
                String filterName = shiroMethodFilter.value();
                PatternsRequestCondition patternsCondition = requestMappingInfo.getPatternsCondition();
                Set<String> urlPatterns = patternsCondition.getPatterns();
                for (String urlPattern : urlPatterns) {
                    log.error("[Shiro URL Filter] {}**={}", urlPattern, filterName);
                    filterChainDefinitionMap.put(urlPattern + "**", filterName);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> e : filterChainDefinitionMap.entrySet()) {
            sb.append(e.getKey()).append(EQ).append(e.getValue()).append(LINE);
        }
        log.error("[Shiro URL Filter] All Filter UrlPatterns definitions\n{}", sb);
        setFilterChainDefinitionMap(filterChainDefinitionMap);
        init(context);
    }
    
    protected void init(ApplicationContext applicationContext) throws Exception {}
}