package ua.donetc.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.donetc.security.models.Peronsq;
import ua.donetc.security.repositories.PeopleRepository;
@Service
public class RegistrationService {
    private final PeopleRepository peopleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(PeopleRepository peopleRepository, PasswordEncoder passwordEncoder) {
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
   public void register(Peronsq peronsq){
       peronsq.setPassword(passwordEncoder.encode(peronsq.getPassword()));
       peronsq.setRole("ROLE_USER");
        peopleRepository.save(peronsq);
    }
}
