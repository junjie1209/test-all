package com.test.annotation.validator;

import com.test.annotation.core.InetVersion;
import com.test.annotation.validation.InetPattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2022/8/11
 */
public class InetPatternValidator implements ConstraintValidator<InetPattern, CharSequence> {

    private InetVersion version;
    private String pattern;
    private String message;

    @Override
    public void initialize(InetPattern inetPattern) {
        this.version = inetPattern.version();
        if (this.version == InetVersion.V4) {
            this.pattern = inetPattern.pattern();
            this.message = inetPattern.message();
        } else {
            throw new IllegalStateException("Unsupported inet version: ");
        }
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(value);
        if (m.matches()) {
            return true;
        } else {
            throw new IllegalStateException(this.message);
        }
    }
}
