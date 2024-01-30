package by.javaguru.usecasses.annotation;

import by.javaguru.persistence.model.TypeMessage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class ValidTypeMessageValidator implements ConstraintValidator<ValidTypeMessage, String> {

    @Override
    public boolean isValid(String stringValue, ConstraintValidatorContext constraintValidatorContext) {

        for (TypeMessage msg : TypeMessage.values() ) {
            if (Objects.equals(msg, stringValue)) {
                return true;
            }
        }
        return false;
    }
}
