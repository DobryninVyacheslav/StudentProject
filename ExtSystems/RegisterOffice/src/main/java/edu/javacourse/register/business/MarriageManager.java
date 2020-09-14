package edu.javacourse.register.business;

import edu.javacourse.register.dao.MarriageDao;
import edu.javacourse.register.dao.PersonDao;
import edu.javacourse.register.domain.MarriageCertificate;
import edu.javacourse.register.domain.Person;
import edu.javacourse.register.domain.PersonFemale;
import edu.javacourse.register.domain.PersonMale;
import edu.javacourse.register.view.MarriageRequest;
import edu.javacourse.register.view.MarriageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service("marriageService")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MarriageManager {

    private static final Logger LOG = LoggerFactory.getLogger(MarriageManager.class);
    @Autowired
    private MarriageDao marriageDao;
    @Autowired
    private PersonDao personDao;

    @Transactional()
    public MarriageResponse findMarriageCertificate(MarriageRequest request) {
        LOG.info("findMarriageCertificate called");
        personDao.addPerson(getPerson(1));
        personDao.addPerson(getPerson(2));
        MarriageCertificate marriageCertificate = getMarriageCertificate();
        marriageDao.saveAndFlush(marriageCertificate);

//        marriageDao.findAll();
        marriageDao.findById(1L);

        return new MarriageResponse();
    }

    private MarriageCertificate getMarriageCertificate() {
        MarriageCertificate marriageCertificate = new MarriageCertificate();
        marriageCertificate.setIssueDate(LocalDate.now());
        marriageCertificate.setNumber("12345");
        marriageCertificate.setActive(true);

        List<Person> persons = personDao.findPersons();
        for(Person person : persons) {
            if (person instanceof PersonMale) {
                marriageCertificate.setHusband((PersonMale)person);
            } else {
                marriageCertificate.setWife((PersonFemale)person);
            }
        }
        return marriageCertificate;
    }

    private Person getPerson(int sex) {
        Person m = (sex == 1) ? new PersonMale() : new PersonFemale();
        m.setFirstName("1_" + sex);
        m.setLastName("2_" + sex);
        m.setPatronymic("3_" + sex);
        m.setDateOfBirth(LocalDate.of(1991,3,12));
        return m;
    }
}
