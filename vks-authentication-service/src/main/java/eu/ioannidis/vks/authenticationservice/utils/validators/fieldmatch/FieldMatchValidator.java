package eu.ioannidis.vks.authenticationservice.utils.validators.fieldmatch;

import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    String firstField;

    String secondField;

    String message;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        firstField = constraintAnnotation.firstField();
        secondField = constraintAnnotation.secondField();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        boolean valid = true;
        try {
            final Object firstObj = BeanUtils.getProperty(value, firstField);
            final Object secondObj = BeanUtils.getProperty(value, secondField);

            valid =  firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
        } catch (final Exception ignore) {

        }

        if (!valid){
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(firstField)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return valid;
    }
}
