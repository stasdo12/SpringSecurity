package ua.donetc.security.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.donetc.security.models.Peronsq;


import java.util.Optional;


@Repository
public interface PeopleRepository extends JpaRepository<Peronsq, Integer> {
    Optional<Peronsq> findByUsername(String username);
}