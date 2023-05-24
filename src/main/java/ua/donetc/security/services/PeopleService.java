package ua.donetc.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.donetc.security.models.Peronsq;
import ua.donetc.security.repositories.PeopleRepository;

import java.util.List;

@Service
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }


    public List<Peronsq> allPerson(){
        return peopleRepository.findAll();
    }

    public Peronsq show(int id){
        return peopleRepository.findById(id).orElse(null);
    }

}
