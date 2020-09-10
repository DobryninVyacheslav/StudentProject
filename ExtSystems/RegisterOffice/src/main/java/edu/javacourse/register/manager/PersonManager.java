package edu.javacourse.register.manager;

import edu.javacourse.register.domain.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class PersonManager {
    public static void main(String[] args) {
        SessionFactory sf = buildSessionFactory();
        Session session = sf.openSession();
        session.getTransaction().begin();
        Person newPerson = new Person();
        newPerson.setFirstName("Василий");
        newPerson.setLastName("Сидоров");
        Long id = (Long) session.save(newPerson);
        System.out.println(id);
        session.getTransaction().commit();
        session.close();

        session = sf.openSession();
        Person personFromDb = session.get(Person.class, id);
        System.out.println(personFromDb);
        session.close();

        session = sf.openSession();
        List<Person> personList = session.createQuery("FROM Person", Person.class).list();
        personList.forEach(p -> System.out.println(p));
        session.close();
    }

    private static SessionFactory buildSessionFactory() {
        try {
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml").build();
            Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
            return metadata.getSessionFactoryBuilder().build();
        } catch (Throwable e) {
            System.err.println("Initial SessionFactory creation failed." + e);
            throw new ExceptionInInitializerError(e);
        }
    }
}
