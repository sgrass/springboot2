package org.cx.config;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author grass
 * @date 2017/11/5
 */
public class AutoConfigDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    @Nullable
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    @Nullable
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringWebMvcConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/*"};
    }
}
