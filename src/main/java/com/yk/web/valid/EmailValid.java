package com.yk.web.valid;

import java.lang.annotation.*;
import javax.validation.*;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE}) 
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
public @interface EmailValid {
	String message() default "이메일 형식에 맞게 작성해주세요.";
    Class<?>[] groups() default {}; 
    Class<? extends Payload>[] payload() default {};
}
