package org.cx.validation.constraints;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * @author grass
 * @date 2017/10/21
 */
public class ValidCardNumberConstraintValidator implements ConstraintValidator<ValidCardNum, String> {

    @Override
    public void initialize(ValidCardNum constraintAnnotation) {

    }

    /**
     * 自定义验证
     * 前缀必须以grass开头
     * 后缀必须是数字
     *
     * @param value
     * @param context
     * @return
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //String#split方法中没有npe保护，且该方法使用正则表达式
        String[] parts = StringUtils.split(value, "-");

        if (ArrayUtils.getLength(parts) != 2) {
            return false;
        }

        String prefix = parts[0];
        String sufffix = parts[1];

        boolean isValidPrefix = Objects.equals(prefix, "grass");
        boolean isValidSufffix = StringUtils.isNumeric(sufffix);

        return isValidPrefix && isValidPrefix;
    }
}
