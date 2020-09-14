package edu.javacourse.register.dao;

import edu.javacourse.register.domain.Person;

import javax.persistence.*;
import java.util.List;

public class PersonDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Person> findPersons() {
        Query query = entityManager.createNamedQuery("Person.findPersons");
        return query.getResultList();
    }

    public Long addPerson(Person person) {
        entityManager.persist(person);
        entityManager.flush();
        return person.getPersonId();
    }
}
