package org.cx.annotation;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * @author grass
 * @date 2017/11/5
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(OnClassCondition.class)
public @interface ConditionalOnClass {

    Class<?>[] value();

}
