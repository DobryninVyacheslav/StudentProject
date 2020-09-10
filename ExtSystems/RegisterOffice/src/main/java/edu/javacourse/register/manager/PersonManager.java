package edu.javacourse.register.manager;

import edu.javacourse.register.domain.Person;
import jdk.jshell.PersistentSnippet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class PersonManager {
    public static void main(String[] args) {
        sessionExample();

        jpaExample();
    }

    private static void jpaExample() {
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Person newPerson = new Person();
        newPerson.setFirstName("Алексей");
        newPerson.setLastName("Федоров");
        em.persist(newPerson);
        System.out.println(newPerson.getPersonId());
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        List resultList = em.createQuery("FROM Person").getResultList();
        resultList.forEach(p -> System.out.println(p));
        em.close();
    }

    private static void sessionExample() {
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
