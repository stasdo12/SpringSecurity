package ua.donetc.security.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.donetc.security.models.Peronsq;
import ua.donetc.security.services.PersonDetailsService;
@Component
public class PersonValidator implements Validator {
    private final PersonDetailsService personDetailsService;

    @Autowired
    public PersonValidator(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Peronsq.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Peronsq peronsq = (Peronsq) target;
        try {
            personDetailsService.loadUserByUsername(peronsq.getUsername());
        }catch (UsernameNotFoundException ignored){
            return;
        }
        errors.rejectValue("username", "", "person with this name available");
    }
}
