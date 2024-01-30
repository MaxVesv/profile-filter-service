package by.javaguru.usecasses.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Set;

@RequiredArgsConstructor
public class ValidServiceNameValidator implements ConstraintValidator<ValidServiceName, String> {

    @Autowired
    @Qualifier("getAllEnabledServiceForFilter")
    private final Set<String> allServices;

    @Override
    public boolean isValid(String nameService, ConstraintValidatorContext constraintValidatorContext) {

        long countService = allServices.stream().map(s -> s.equals(nameService)).count();
        if (countService > 0) {
            return true;
        }
        return false;
    }
}
