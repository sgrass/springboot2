package org.cx.annotation;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;

import java.util.List;

/**
 * @author grass
 * @date 2017/11/5
 */
public class OnClassCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        boolean matched = false;

        /**
         * 获取所有的 ConditionalOnClass 中的属性方法
         */
        MultiValueMap<String, Object> attributes = metadata.getAllAnnotationAttributes(ConditionalOnClass.class.getName());
        //获取 value() 方法中的配置值

        List<Object> classes = (List<Object>)attributes.get("value");

        try {
            for (Object klass : classes) {
                Class<?>[] type = (Class<?>[]) klass; // 如果异常的话，说明class 不存在的
                matched = true;
            }
        }catch (Throwable error){
            matched = false;
        }
        System.out.println("OnClassCondition 是否匹配 :" + matched);
        return matched;
    }
}
