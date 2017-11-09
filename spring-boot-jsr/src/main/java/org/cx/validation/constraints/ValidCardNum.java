package org.cx.validation.constraints;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author grass
 * @date 2017/10/21
 */

@Target(FIELD)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { ValidCardNumberConstraintValidator.class})
public @interface ValidCardNum {

    /**
     * 从配置文件中读取默认错误信息
     * @return
     */
    String message() default "{user.validation.constraints.card.number.failed.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
