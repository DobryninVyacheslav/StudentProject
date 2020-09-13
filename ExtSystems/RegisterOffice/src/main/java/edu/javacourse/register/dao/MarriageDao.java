package edu.javacourse.register.dao;

import edu.javacourse.register.domain.MarriageCertificate;
import edu.javacourse.register.view.MarriageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class MarriageDao {

    private static final Logger LOG = LoggerFactory.getLogger(MarriageDao.class);
    private EntityManager entityManager;
    @Value("${test.value}")
    private String test;

    public void setTest(String test) {
        this.test = test;
    }

    public MarriageCertificate findMarriageCertificate(MarriageRequest request) {
        LOG.info("findMarriageCertificate called:{}", test);

        return new MarriageCertificate();
    }
}
